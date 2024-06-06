import unittest
from datetime import datetime

import pandas as pd
import financial_appraisal as fa

class test_financial_appraisal(unittest.TestCase):
    def test_cal_valuation_with_valid_data(self):
        # 获取当前日期
        current_date = datetime.now()

        # 调用函数
        report_date = fa.ainf_get_latest_report_date(current_date)
        # start_date转成date类型
        start_date = datetime.strptime('2020-03-01', '%Y-%m-%d')
        result = fa.cal_valuation(['300291', '000967', '603816'], start_date, report_date)

if __name__ == '__main__':
    unittest.main()