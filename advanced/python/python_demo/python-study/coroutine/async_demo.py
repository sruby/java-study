import asyncio
import time


async def crawl_page(url):
    print('crawling {}'.format(url))
    sleep_time = int(url.split('_')[-1])
    await asyncio.sleep(sleep_time)
    print('OK {}'.format(url))

async def main(urls):
    start_time = time.time()  # 记录开始时间
    for url in urls:
        await crawl_page(url)
    end_time = time.time()  # 记录结束时间
    print("Total time: {:.2f} seconds".format(end_time - start_time))  # 输出总耗时

asyncio.run(main(['url_1', 'url_2', 'url_3', 'url_4']))
