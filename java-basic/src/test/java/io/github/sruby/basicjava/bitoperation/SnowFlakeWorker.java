package io.github.sruby.basicjava.bitoperation;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class SnowFlakeWorker {

    private volatile static SnowFlakeWorker snowFlakeWorkerInstance;

    // 1位标识部分    -      41位时间戳部分        -         10位节点部分     12位序列号部分
    /** 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 */
    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1288834974657L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 10;  // 序列号占用的位数
    private final static long WORK_BIT = 10;    // 机器标识占用的位数

    //5位的机器id
    public static final long WORKER_ID_BITS = 5L;
    //5位的机房id；。‘
    public static final long DATACENTER_ID_BITS = 5L;

    // 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
    public static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    // 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
    public static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);

    private long workerIdShift = SEQUENCE_BIT;
    private long datacenterIdShift = SEQUENCE_BIT + WORKER_ID_BITS;
    private long timestampLeftShift = SEQUENCE_BIT + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * WORK_NUM最大值 1023
     */
    private final static long MAX_WORK_NUM = -1L ^ (-1L << WORK_BIT);
    /**
     * SEQUENCE最大值 4095
     */
    public final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long WORK_LEFT = SEQUENCE_BIT;
    private final static long TIMESTMP_LEFT = WORK_LEFT + WORK_BIT;

    //机房ID 2进制5位  32位减掉1位 31个
    private long datacenterId;
    private long workId;
    private long sequence = 0L;  //序列号
    private long lastTimestamp = -1L; //上一次时间戳

    /**
     * 步长, 1024
     */
    public static long stepSize = 2 << 9;
    /**
     * 基础序列号, 每发生一次时钟回拨即改变, basicSequence += stepSize
     */
    private long basicSequence = 0L;

    private SnowFlakeWorker(long datacenterId, long workId) {
        // 检查机房id和机器id是否超过31 不能小于0
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }

        if (workId > MAX_WORKER_ID || workId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }

        this.datacenterId = datacenterId;
        this.workId = workId;
        this.lastTimestamp = getSecond();
    }


    public synchronized static SnowFlakeWorker initSnowFlakeWorker(long dataCenterId, long workId) {
        snowFlakeWorkerInstance = new SnowFlakeWorker(dataCenterId, workId);
        return snowFlakeWorkerInstance;
    }

    public static SnowFlakeWorker getInstance() {
        return snowFlakeWorkerInstance;
    }


    /**
     * 产生下一个ID
     */
    public synchronized long nextId(boolean addDatePrefix) {
        long currSecond = getSecond();
        if (currSecond < lastTimestamp) {
            return handleClockBackwards(currSecond, addDatePrefix);
        }
        log.info("currSecond:{},lastTimestamp:{},sequence:{}",currSecond,lastTimestamp,sequence);
        if (currSecond == lastTimestamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currSecond = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为 basicSequence
            sequence = basicSequence;
        }

        lastTimestamp = currSecond;

        LocalDateTime now = LocalDateTime.now();
        String day = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        long timeFly = now.toLocalTime().toSecondOfDay();
        long id = timeFly << timestampLeftShift |
                (datacenterId << datacenterIdShift) |
                (workId << workerIdShift) | sequence;

        long snowId = addDatePrefix ? Long.parseLong(day + id) : id;
        return snowId;
    }

    /**
     * 获取带业务前缀和日期前缀的分布式id
     * @param prefix
     * @return
     */
    public synchronized String nextIdWithBusinessPrefix(String prefix) {
        return prefix + nextId(true);
    }

    /**
     * 获取带日期前缀的分布式id
     * @return
     */
    public synchronized long nextIdWithDatePrefix() {
        return nextId(true);
    }

    /**
     * 获取不带任何前缀的分布式id
     * @return
     */
    public synchronized long nextId() {
        return nextId(false);
    }

    /**
     * 处理时钟回拨
     */
    private long handleClockBackwards(long currStmp, boolean addDatePrefix) {
        basicSequence += stepSize;
        if (basicSequence == MAX_SEQUENCE + 1) {
            basicSequence = 0;
            currStmp = getSecond();
        }
        sequence = basicSequence;

        lastTimestamp = currStmp;

        LocalDateTime now = LocalDateTime.now();
        String day = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        long timeFly = now.toLocalTime().toSecondOfDay();
        long id = timeFly << timestampLeftShift |
                (datacenterId << datacenterIdShift) |
                (workId << workerIdShift) | sequence;
        long snowId = addDatePrefix ? Long.parseLong(day + id) : id;
        return snowId;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        log.info("get next mill");
        while (mill <= lastTimestamp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        LocalDateTime now = LocalDateTime.now();
        return now.toLocalTime().toSecondOfDay();
    }

    private long getSecond(){
        return System.currentTimeMillis() / 1000;
    }
}
