import asyncio
import base64

import websockets
import json
import random
import time
import logging
import traceback
from google.protobuf.json_format import MessageToJson, Parse, MessageToDict
from decode_bytes_util import decode_bytes_fields
import complex_message_pb2 as cm

# 配置日志
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)


class WebSocketClient:
    def __init__(self, uri="ws://localhost:8765"):
        self.uri = uri
        self.websocket = None
        self.running = True
        self.send_queue = asyncio.Queue()

    async def connect(self):
        self.websocket = await websockets.connect(self.uri)
        logger.info(f"Connected to {self.uri}")

    async def send_message(self, message):
        await self.send_queue.put(message)

    async def send_loop(self):
        while self.running:
            try:
                message = await self.send_queue.get()
                if self.websocket is None:
                    logger.error("Not connected to a WebSocket server")
                    continue

                serialized_message = message.SerializeToString()
                await self.websocket.send(serialized_message)
                logger.info(f"Sent message: {MessageToJson(message)}")
            except Exception as e:
                logger.error(f"Error sending message: {e}\n{traceback.format_exc()}")

    from google.protobuf.json_format import MessageToDict

    async def listen_for_responses(self):
        while self.running:
            try:
                response = await self.websocket.recv()

                proto_message = cm.ResponseMessage()
                proto_message.ParseFromString(response)

                # 解码所有 bytes 字段
                wrapped_message = decode_bytes_fields(proto_message)

                # 转换为字典
                message_dict = MessageToDict(wrapped_message._proto_message)

                # 添加解码后的字段
                for key, value in wrapped_message._extra_attributes.items():
                    message_dict[key] = value

                # 特别处理 responseContent 字段
                if 'responseContent' in message_dict:
                    original_content = message_dict['responseContent']
                    decoded_content = base64.b64decode(original_content).decode('utf-8', errors='replace')
                    message_dict['responseContent_decoded'] = decoded_content

                logger.info(f"Received response: {json.dumps(message_dict, ensure_ascii=False, indent=2)}")
            except websockets.exceptions.ConnectionClosed:
                logger.error(f"Connection to server closed\n{traceback.format_exc()}")
                self.running = False
            except Exception as e:
                logger.error(f"An error occurred while receiving: {e}\n{traceback.format_exc()}")
        logger.info("Stopped listening for responses")

    async def start(self):
        await self.connect()
        listen_task = asyncio.create_task(self.listen_for_responses())
        send_task = asyncio.create_task(self.send_loop())
        return listen_task, send_task

    async def stop(self):
        self.running = False
        if self.websocket:
            await self.websocket.close()


class ClientManager:
    def __init__(self, client: WebSocketClient):
        self.client = client

    def create_simple_message(self):
        message = cm.SimpleMessage()
        message.message_id = int(time.time() * 1000)  # Use milliseconds as message_id
        message.type = cm.SimpleMessage.MessageType.REQUEST

        # Generate a random string and encode it using GBK
        content_str = ''.join(random.choice('你好世界HelloWorld0123456789') for _ in range(20))
        message.content = content_str.encode('gbk')

        # Add some random flags
        message.flags.extend([random.randint(1, 1000) for _ in range(3)])

        return message

    async def process_and_send(self):
        # while True:
            try:
                await asyncio.sleep(random.uniform(1, 5))  # Random wait between 1-5 seconds
                message = self.create_simple_message()
                await self.client.send_message(message)
            except Exception as e:
                logger.error(f"Error in process_and_send: {e}\n{traceback.format_exc()}")

from google.protobuf.descriptor import FieldDescriptor

async def main():
    client = WebSocketClient()
    manager = ClientManager(client)

    listen_task, send_task = await client.start()

    try:
        process_task = asyncio.create_task(manager.process_and_send())
        logger.info("Client started. Press Ctrl+C to exit.")
        await asyncio.gather(listen_task, send_task, process_task)
    except KeyboardInterrupt:
        logger.info("\nInterrupted by user. Shutting down...")
    except Exception as e:
        logger.error(f"An error occurred: {e}\n{traceback.format_exc()}")
    finally:
        await client.stop()
        listen_task.cancel()
        send_task.cancel()
        process_task.cancel()
        try:
            await listen_task
            await send_task
            await process_task
        except asyncio.CancelledError:
            pass


if __name__ == "__main__":
    asyncio.run(main())