package io.github.sruby.rocketmq;

import com.sun.jersey.spi.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.Validators;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageAccessor;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.PUT;
import java.util.concurrent.ExecutorService;

@Slf4j
public class CreateOrderService {

    @Inject
    private OrderDao orderDao; // 注入订单表的DAO
    @Inject
    private ExecutorService executorService; //注入一个ExecutorService

    private TransactionMQProducer producer;
    private DefaultMQProducer defaultMQProducer;

    // 初始化transactionListener 和 producer
//  @Init
    public void init() throws MQClientException {
        TransactionListener transactionListener = createTransactionListener();
        producer = new TransactionMQProducer("myGroup");
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();
    }

    // 创建订单服务的请求入口
    @PUT
    @RequestMapping("")
    public boolean createOrder(@RequestBody CreateOrderRequest request) throws MQClientException {
        // 根据创建订单请求创建一条消息
        Message msg = createMessage(request);
        // 发送事务消息
        SendResult sendResult = producer.sendMessageInTransaction(msg, request);
        // 返回：事务是否成功
        return sendResult.getSendStatus() == SendStatus.SEND_OK;
    }

    private Message createMessage(CreateOrderRequest request) {
        return null;
    }


    public TransactionSendResult sendMessageInTransaction(final Message msg,
                                                          final LocalTransactionExecuter localTransactionExecuter, final Object arg)
            throws MQClientException {
        TransactionListener transactionListener = getCheckListener();
        if (null == localTransactionExecuter && null == transactionListener) {
            throw new MQClientException("tranExecutor is null", null);
        }
        Validators.checkMessage(msg, this.defaultMQProducer);

        SendResult sendResult = null;

        // 这里给消息添加了属性，标明这是一个事务消息，也就是半消息
        MessageAccessor.putProperty(msg, MessageConst.PROPERTY_TRANSACTION_PREPARED, "true");
        MessageAccessor.putProperty(msg, MessageConst.PROPERTY_PRODUCER_GROUP, this.defaultMQProducer.getProducerGroup());

        // 调用发送普通消息的方法，发送这条半消息
        try {
            sendResult = this.send(msg);
        } catch (Exception e) {
            throw new MQClientException("send message Exception", e);
        }

        LocalTransactionState localTransactionState = LocalTransactionState.UNKNOW;
        Throwable localException = null;
        switch (sendResult.getSendStatus()) {
            case SEND_OK: {
                try {
                    if (sendResult.getTransactionId() != null) {
                        msg.putUserProperty("__transactionId__", sendResult.getTransactionId());
                    }
                    String transactionId = msg.getProperty(MessageConst.PROPERTY_UNIQ_CLIENT_MESSAGE_ID_KEYIDX);
                    if (null != transactionId && !"".equals(transactionId)) {
                        msg.setTransactionId(transactionId);
                    }

                    // 执行本地事务
                    if (null != localTransactionExecuter) {
                        localTransactionState = localTransactionExecuter.executeLocalTransactionBranch(msg, arg);
                    } else if (transactionListener != null) {
                        log.debug("Used new transaction API");
                        localTransactionState = transactionListener.executeLocalTransaction(msg, arg);
                    }
                    if (null == localTransactionState) {
                        localTransactionState = LocalTransactionState.UNKNOW;
                    }

                    if (localTransactionState != LocalTransactionState.COMMIT_MESSAGE) {
                        log.info("executeLocalTransactionBranch return {}", localTransactionState);
                        log.info(msg.toString());
                    }
                } catch (Throwable e) {
                    log.info("executeLocalTransactionBranch exception", e);
                    log.info(msg.toString());
                    localException = e;
                }
            }
            break;
            case FLUSH_DISK_TIMEOUT:
            case FLUSH_SLAVE_TIMEOUT:
            case SLAVE_NOT_AVAILABLE:
                localTransactionState = LocalTransactionState.ROLLBACK_MESSAGE;
                break;
            default:
                break;
        }

        // 根据事务消息和本地事务的执行结果localTransactionState，决定提交或回滚事务消息
        // 这里给Broker发送提交或回滚事务的RPC请求。
        try {
            this.endTransaction(sendResult, localTransactionState, localException);
        } catch (Exception e) {
            log.warn("local transaction execute " + localTransactionState + ", but end broker transaction failed", e);
        }

        TransactionSendResult transactionSendResult = new TransactionSendResult();
        transactionSendResult.setSendStatus(sendResult.getSendStatus());
        transactionSendResult.setMessageQueue(sendResult.getMessageQueue());
        transactionSendResult.setMsgId(sendResult.getMsgId());
        transactionSendResult.setQueueOffset(sendResult.getQueueOffset());
        transactionSendResult.setTransactionId(sendResult.getTransactionId());
        transactionSendResult.setLocalTransactionState(localTransactionState);
        return transactionSendResult;
    }

    private SendResult send(Message msg) {
        return null;
    }

    private void endTransaction(SendResult sendResult, LocalTransactionState localTransactionState, Throwable localException) {

    }

    private TransactionListener getCheckListener() {
        return null;
    }

    private TransactionListener createTransactionListener() {
        return new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                CreateOrderRequest request = (CreateOrderRequest) arg;
                try {
                    // 执行本地事务创建订单
                    orderDao.createOrderInDB(request);
                    // 如果没抛异常说明执行成功，提交事务消息
                    return LocalTransactionState.COMMIT_MESSAGE;
                } catch (Throwable t) {
                    // 失败则直接回滚事务消息
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }

            // 反查本地事务
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                // 从消息中获得订单ID
                String orderId = msg.getUserProperty("orderId");

                // 去数据库中查询订单号是否存在，如果存在则提交事务；
                // 如果不存在，可能是本地事务失败了，也可能是本地事务还在执行，所以返回UNKNOW
                //（PS：这里RocketMQ有个拼写错误：UNKNOW）
                return orderDao.isOrderIdExistsInDB(orderId) ?
                        LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.UNKNOW;
            }
        };
    }

}