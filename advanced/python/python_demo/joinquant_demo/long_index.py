from jqdatasdk import *
import auth_my

auth_my.auth_call()

# 设置参考标的
security = '000300.XSHG'  # 沪深300
# 设置我们要操作的股票池
# set_universe([security])
# # 设置交易条件
# set_option('use_real_price', True)
# # 运行频率
# run_daily(trade, 'every_bar')
# 初始化交易状态记录
traded_low3 = False
traded_low5 = False
traded_high3 = False
traded_high5 = False

# 每天交易时
def trade(context):
    global traded_low3
    global traded_low5
    global traded_high3
    global traded_high5

    # 获取当前时间
    current_date = context.current_dt.date()
    # 获取过去3年和5年的历史数据
    hist3 = get_price(security, end_date=current_date, count=3*252, frequency='1d')
    hist5 = get_price(security, end_date=current_date, count=5*252, frequency='1d')

    # 计算3年和5年内的最高点和最低点
    low3 = hist3['low'].min()
    high3 = hist3['high'].max()
    low5 = hist5['low'].min()
    high5 = hist5['high'].max()

    # 获取当前价格
    current_price = get_close_price(security, 1, '1m')
    print("low3:{},high3:{},low5:{},high5:{},current_price:{}",low3,high3,low5,high5,current_price)

    # 根据价格决定交易策略
    if current_price <= low3 and not traded_low3:
        order_target_percent(security, 0.3)
        traded_low3 = True
    elif current_price <= low5 and not traded_low5:
        order_target_percent(security, 1)
        traded_low5 = True
    elif current_price >= high3 and not traded_high3:
        order_target_percent(security, 0.7)
        traded_high3 = True
    elif current_price >= high5 and not traded_high5:
        order_target_percent(security, 0)
        traded_high5 = True

# 获取收盘价
def get_close_price(security, count, unit):
    return attribute_history(security, count, unit, 'close',df=False)['close'][-1]