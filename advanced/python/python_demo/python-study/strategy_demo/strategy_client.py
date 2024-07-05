import schedule
import time
from datetime import datetime

class Scheduler:
    def __init__(self, data_receiver):
        self.data_receiver = data_receiver

    def daily_task(self):
        print(f"执行每日任务，当前时间: {datetime.now()}")
        print(f"变量a中的数据: {self.data_receiver.get_data()}")

    def setup_schedule(self):
        # schedule.every().day.at("00:00").do(self.daily_task)
        schedule.every(10).seconds.do(self.daily_task)

    def run_schedule(self):
        while True:
            schedule.run_pending()
            time.sleep(1)