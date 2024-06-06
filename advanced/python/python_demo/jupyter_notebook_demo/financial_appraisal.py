import pandas as pd
from utils.Database import Database
from datetime import datetime
from dateutil.relativedelta import relativedelta


def cal_valuation(ticker_symbols, start_date, end_date):
    # 读取数据
    # 初始化Database.py
    db = Database()
    # 查询股票对应的行业的所有股票
    sub_inst_stock = query_ticker_symbols(db, end_date, start_date, ticker_symbols)
    #     获取symbols_list中的TICKER_SYMBOL
    all_ticker_symbols = sub_inst_stock['TICKER_SYMBOL'].tolist()

    #        查询日期区间范围内每个季度最后一个交易日 修改sub_quarter_dates
    sub_quarter_dates = query_trade_dates(db, end_date, start_date)
    # 获取sub_quarter_dates中的CALENDAR_DATE
    sub_quarter_dates_list = sub_quarter_dates['CALENDAR_DATE'].tolist()

    # 查询财务数据 SUB_RANK_SUBQUERY
    financial_data = query_finance_data(all_ticker_symbols, db, sub_quarter_dates_list)

    #     数据合并
    # Add a temporary key to sub_inst_stock and sub_quarter_dates for cross join
    sub_inst_stock['tmp_key'] = 1
    sub_quarter_dates['tmp_key'] = 1
    # Perform cross join
    sub_inst_stock_date = pd.merge(sub_inst_stock, sub_quarter_dates, on='tmp_key')
    # Drop the temporary key
    sub_inst_stock_date.drop('tmp_key', axis=1, inplace=True)
    # Perform left join with financial_data
    result_df = pd.merge(sub_inst_stock_date, financial_data, how='left', on=['TICKER_SYMBOL', 'CALENDAR_DATE'])

    # 先将DIV_RATE中的NaN值替换为0
    result_df['DIV_RATE'].fillna(0, inplace=True)

    #     计算估值能力：根据result_df中的TYPE_NAME和CALENDAR_DATE分组，按照DIV_RATE顺序排序，计算每个TICKER_SYMBOL所处的百分位（RANK.AVG/每组Count
    result_df['RANK'] = result_df.groupby(['TYPE_NAME', 'CALENDAR_DATE'])['DIV_RATE'].rank(method='average', pct=True)
    # result_df['RANK_PERCENT'] = result_df['RANK'] / result_df.groupby(['TYPE_NAME', 'CALENDAR_DATE'])['DIV_RATE'].transform('count')
    # 在jupyter notebook中以表格形式展示result_df
    return result_df


def query_trade_dates(db, end_date, start_date):
    sql_query_quarter_dates = f'''
        SELECT MAX(CALENDAR_DATE) AS CALENDAR_DATE
                FROM F_DATAYES.md_trade_cal@source_db_link
                WHERE EXCHANGE_CD = 'XSHG'
                AND IS_OPEN = 1
                AND CALENDAR_DATE BETWEEN :start_date AND :end_date
                GROUP BY EXTRACT(YEAR FROM CALENDAR_DATE),
                CASE
                    WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 1 AND 3 THEN 1
                    WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 4 AND 6 THEN 2
                    WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 7 AND 9 THEN 3
                    WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 10 AND 12 THEN 4
                END
        '''
    params = {'start_date': start_date, 'end_date': end_date}
    sub_quarter_dates = db.execute_sql_pd(sql_query_quarter_dates, params)
    return sub_quarter_dates


def query_ticker_symbols(db, end_date, start_date, ticker_symbols):
    ticker_symbols_placeholder = ",".join([":symbol{}".format(i) for i in range(len(ticker_symbols))])
    sql_query_symbols = f'''
    SELECT t4.TYPE_ID, t4.TYPE_NAME, t5.TICKER_SYMBOL, t5.SEC_SHORT_NAME, T5.EXCHANGE_CD, t5.PARTY_ID
            FROM F_DATAYES.MD_INST_TYPE@source_db_link t3
            JOIN (
                SELECT t0.TICKER_SYMBOL, t0.EXCHANGE_CD, t1.TYPE_ID, t2.TYPE_NAME, t1.INTO_DATE, t1.OUT_DATE
                FROM F_DATAYES.MD_SECURITY@source_db_link T0
                JOIN F_DATAYES.MD_INST_TYPE@source_db_link T1 ON T0.PARTY_ID = T1.PARTY_ID
                JOIN F_DATAYES.MD_TYPE@source_db_link T2 ON T1.TYPE_ID = T2.TYPE_ID
                WHERE t2.INDUSTRY = '中信行业分类'   -- 中信行业分类, GICS行业分类, 中证行业, 申万行业分类(2021)分类   等
                AND T0.EXCHANGE_CD IN ('XSHE', 'XSHG', 'XBEI', 'XHKG')
                AND :start_date >= T1.INTO_DATE
                AND :end_date < COALESCE(T1.OUT_DATE, date '9999-12-31')
                AND t1.OUT_DATE IS NULL
                AND T0.ASSET_CLASS = 'E'
                AND TICKER_SYMBOL IN ({ticker_symbols_placeholder})
                ORDER BY t1.INTO_DATE DESC
            ) t4 ON t3.TYPE_ID = t4.TYPE_ID
            JOIN F_DATAYES.MD_SECURITY@source_db_link t5 ON t3.PARTY_ID = t5.PARTY_ID
            WHERE t5.EXCHANGE_CD IN ('XSHE', 'XSHG', 'XBEI', 'XHKG')
            AND :end_date >= t3.INTO_DATE
            AND :end_date < COALESCE(t3.OUT_DATE, date '9999-12-31')
            AND t5.ASSET_CLASS = 'E'
              and t5.DY_USE_FLG = 1
              and LIST_STATUS_CD = 'L'
            AND t3.OUT_DATE IS NULL
    '''
    params = {'start_date': start_date, 'end_date': end_date}
    params.update({f"symbol{i}": symbol for i, symbol in enumerate(ticker_symbols)})
    sub_inst_stock = db.execute_sql_pd(sql_query_symbols, params)
    return sub_inst_stock


def query_finance_data(all_ticker_symbols, db, sub_quarter_dates):
    # 为每个元素创建一个占位符
    all_ticker_symbols_placeholder = ",".join([":symbol{}".format(i) for i in range(len(all_ticker_symbols))])
    sub_quarter_dates_placeholder = ",".join([":date{}".format(i) for i in range(len(sub_quarter_dates))])
    # 在SQL查询中使用这些占位符
    sql_query_financial_data = f'''
        SELECT T0.TICKER_SYMBOL, T0.TRADE_DATE CALENDAR_DATE, T0.DIV_RATE
        FROM F_DATAYES.mkt_rank_div_yield@source_db_link T0
        WHERE T0.TICKER_SYMBOL IN ({all_ticker_symbols_placeholder})
        AND T0.TRADE_DATE IN ({sub_quarter_dates_placeholder})
        '''
    # 创建一个字典，将每个占位符映射到其对应的值
    params = {f"symbol{i}": symbol for i, symbol in enumerate(all_ticker_symbols)}
    params.update({f"date{i}": date for i, date in enumerate(sub_quarter_dates)})
    # 执行SQL查询
    financial_data = db.execute_sql_pd(sql_query_financial_data, params)
    return financial_data


def ainf_get_latest_report_date(p_date):
    if 11 <= p_date.month <= 12 or 1 <= p_date.month <= 4:
        v_report_date = datetime(p_date.year, 1, 1) + relativedelta(months=9, days=-1)
    elif 5 <= p_date.month <= 8:
        v_report_date = datetime(p_date.year, 1, 1) + relativedelta(months=3, days=-1)
    elif 9 <= p_date.month <= 10:
        v_report_date = datetime(p_date.year, 1, 1) + relativedelta(months=6, days=-1)
    return v_report_date
