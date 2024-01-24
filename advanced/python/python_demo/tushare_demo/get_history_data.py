import tushare as ts
from dotenv import load_dotenv
import os

load_dotenv()  # This is the crucial part
token = os.getenv('TOKEN')
ts.set_token(token)
pro = ts.pro_api()

#查询当前所有正常上市交易的股票列表
# data = pro.stock_basic(exchange='', list_status='L', fields='ts_code,symbol,name,area,industry,list_date')
# print(data)

df = pro.daily(ts_code='000001.SZ', start_date='20180701', end_date='20180718')
print(df)
#多个股票
df = pro.daily(ts_code='000001.SZ,600000.SH', start_date='20180701', end_date='20180718')
print(df)