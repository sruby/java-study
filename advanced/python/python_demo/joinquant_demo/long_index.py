# 标题：均线策略-沪深300指数5日vs3年均线

def initialize(context):
    # 设置操作对象和参考标准
    g.security = '000300.XSHG'

    set_benchmark('000300.XSHG')
    # 设置手续费，指数基金的手续费设置为万一, 每笔交易最低扣5块钱
    set_commission(PerTrade(buy_cost=0.0001, sell_cost=0.0001, min_cost=5))
    # 千分之一的滑点费用
    set_slippage(FixedSlippage(0.001))


# 按天回测
def handle_data(context, data):
    log.info('调仓日期：%s' % context.current_dt.date())
    security = g.security
    # 获取股票的收盘价,df: 若是True, 返回[pandas.DataFrame], 否则返回一个dict
    close_data = attribute_history(security, 1095, '1d', ['close'], df=False)
    # print(f"close_data: {close_data}")
    # 取得过去5天的平均价格，mean为均值函数，[-5:] 取出该列最后(最近)的5个元素
    # ma5 = close_data['close'][-5:].mean()
    # 取得上一时间点价格
    current_price = close_data['close'][-1:]
    log.info("current_price: %s" % (current_price))

    # last_price = close_data['close']['2023-10-13']
    # log.info("last_price: %s" % (last_price))
    # 取得过去3年的最低价格
    min1095 = close_data['close'].min()
    log.info("min1095: %s" % (min1095))
    # 过去3年最高价格
    max1095 = close_data['close'].max()
    log.info("max1095: %s" % (max1095))
    # 取得当前的现金
    cash = context.portfolio.available_cash

    index_etf = '510310.XSHG'

    # 小于三年最低价格，买入一半
    if (current_price <= min1095):
        # 用所有 cash 买入股票
        order_value(index_etf, cash / 2)
        # 记录这次买入
        log.info("Buying %s" % (index_etf))
    # 大于三年最高均线，卖出一半
    elif current_price >= max1095:
        # 卖出一半
        # order_target(index_etf, 0)
        sell_half_position(context, index_etf)
        # 记录这次卖出
        log.info("Selling %s" % (index_etf))
    # 画出当前的价格
    # record(current_price = data[security].price)


# Assuming you are within the context of a JoinQuant strategy function

# This function will sell half of the position for the given stock code
def sell_half_position(context, stock_code):
    # Get current positions
    positions = context.portfolio.positions

    # Check if the stock is in our positions
    if stock_code in positions:
        # Get the position for the given stock
        position = positions[stock_code]

        # Calculate the quantity to sell (half of the current position)
        # Make sure to convert it to an integer, as you can't sell partial shares
        quantity_to_sell = int(position.quantity / 2)

        # Create a sell order for half the position
        if quantity_to_sell > 0:
            order = order_target_value(stock_code, position.market_value / 2)
            if order:
                print("Sold half of the position for stock:", stock_code)
            else:
                print("Order failed for stock:", stock_code)
        else:
            print("Not enough quantity to sell for stock:", stock_code)
    else:
        print("No position for stock:", stock_code)