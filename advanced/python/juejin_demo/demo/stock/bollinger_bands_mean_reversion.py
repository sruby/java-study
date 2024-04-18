# coding=utf-8
from __future__ import print_function, absolute_import
from gm.api import *


"""
本策略采用布林线进行均值回归交易。当价格触及布林线上轨的时候进行卖出，当触及下轨的时候，进行买入。
使用600004在 2009-09-17 13:00:00 到 2020-03-21 15:00:00 进行了回测。
注意：
1：实盘中，如果在收盘的那一根bar或tick触发交易信号，需要自行处理，实盘可能不会成交。
"""


# 策略中必须有init方法
def init(context):
    # 设置布林线的三个参数
    context.maPeriod = 26  # 计算BOLL布林线中轨的参数
    context.stdPeriod = 26  # 计算BOLL 标准差的参数
    context.stdRange = 1  # 计算BOLL 上下轨和中轨距离的参数

    # 设置要进行回测的合约
    context.symbol = 'SHSE.600004'  # 订阅&交易标的, 此处订阅的是600004
    context.period = max(context.maPeriod, context.stdPeriod, context.stdRange) + 1  # 订阅数据滑窗长度

    # 订阅行情
    subscribe(symbols= context.symbol, frequency='1d', count=context.period)


def on_bar(context, bars):

    # 获取数据滑窗，只要在init里面有订阅，在这里就可以取的到，返回值是pandas.DataFrame
    data = context.data(symbol=context.symbol, frequency='1d', count=context.period, fields='close')

    # 计算boll的上下界
    bollUpper = data['close'].rolling(context.maPeriod).mean() \
                + context.stdRange * data['close'].rolling(context.stdPeriod).std()
    bollBottom = data['close'].rolling(context.maPeriod).mean() \
                 - context.stdRange * data['close'].rolling(context.stdPeriod).std()

    # 获取现有持仓
    pos = context.account().position(symbol=context.symbol, side=PositionSide_Long)

    # 交易逻辑与下单
    # 当有持仓，且股价穿过BOLL上界的时候卖出股票。
    if data.close.values[-1] > bollUpper.values[-1] and data.close.values[-2] < bollUpper.values[-2]:
        if pos:  # 有持仓就市价卖出股票。
            order_volume(symbol=context.symbol, volume=100, side=OrderSide_Sell,
                         order_type=OrderType_Market, position_effect=PositionEffect_Close)
            print('以市价单卖出一手')

    # 当没有持仓，且股价穿过BOLL下界的时候买出股票。
    elif data.close.values[-1] < bollBottom.values[-1] and data.close.values[-2] > bollBottom.values[-2]:
        if not pos:  # 没有持仓就买入一百股。
            order_volume(symbol=context.symbol, volume=100, side=OrderSide_Buy,
                         order_type=OrderType_Market, position_effect=PositionEffect_Open)
            print('以市价单买入一手')


if __name__ == '__main__':
    '''
        strategy_id策略ID,由系统生成
        filename文件名,请与本文件名保持一致
        mode实时模式:MODE_LIVE回测模式:MODE_BACKTEST
        token绑定计算机的ID,可在系统设置-密钥管理中生成
        backtest_start_time回测开始时间
        backtest_end_time回测结束时间
        backtest_adjust股票复权方式不复权:ADJUST_NONE前复权:ADJUST_PREV后复权:ADJUST_POST
        backtest_initial_cash回测初始资金
        backtest_commission_ratio回测佣金比例
        backtest_slippage_ratio回测滑点比例
        '''
    run(strategy_id='strategy_id',
        filename='main.py',
        mode=MODE_BACKTEST,
        token='token_id',
        backtest_start_time='2009-09-17 13:00:00',
        backtest_end_time='2020-03-21 15:00:00',
        backtest_adjust=ADJUST_PREV,
        backtest_initial_cash=1000,
        backtest_commission_ratio=0.0001,
        backtest_slippage_ratio=0.0001)
