import asyncio
from quota_client import DataReceiver
from strategy_client import Scheduler

async def main():
    # 创建 DataReceiver 实例
    data_receiver = DataReceiver("ws://your_websocket_server_url")

    # 创建 Scheduler 实例
    scheduler = Scheduler(data_receiver)
    scheduler.setup_schedule()

    # 创建并运行事件循环
    loop = asyncio.get_event_loop()

    # 运行WebSocket接收数据的协程
    websocket_task = loop.create_task(data_receiver.receive_data())

    # 运行定时任务
    schedule_task = loop.run_in_executor(None, scheduler.run_schedule)

    try:
        await asyncio.gather(websocket_task, schedule_task)
    except KeyboardInterrupt:
        print("程序被中断")
    finally:
        websocket_task.cancel()
        schedule_task.cancel()


if __name__ == "__main__":
    asyncio.run(main())