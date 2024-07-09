import asyncio
import websockets
import json
import logging
import traceback
from google.protobuf.json_format import MessageToJson, Parse
import complex_message_pb2 as cm
from decode_bytes_util import decode_bytes_fields

# 配置日志
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)


class WebSocketServer:
    def __init__(self, host="localhost", port=8765):
        self.host = host
        self.port = port
        self.clients = set()

    async def handle_client(self, websocket, path):
        self.clients.add(websocket)
        try:
            async for message in websocket:
                try:
                    # 解析接收到的 Protocol Buffers 消息
                    proto_message = cm.SimpleMessage()
                    proto_message.ParseFromString(message)

                    # 解码所有 bytes 字段
                    decoded_message = decode_bytes_fields(proto_message)

                    # 创建响应消息
                    response = cm.ResponseMessage()
                    response.original_message_id = decoded_message.message_id
                    response.response_content = f"Server received: {decoded_message.content}".encode('gbk')
                    response.status = cm.ResponseMessage.ResponseStatus.OK

                    # 发送响应
                    await websocket.send(response.SerializeToString())

                    # 解码响应消息用于日志记录
                    decoded_response = decode_bytes_fields(response)
                    logger.info(f"Sent response: {MessageToJson(decoded_response)}")

                except Exception as e:
                    logger.error(f"Error processing message: {e}", exc_info=True)
                    # 错误处理代码...

        except websockets.exceptions.ConnectionClosed:
            logger.info("Client disconnected")
        finally:
            self.clients.remove(websocket)

    async def start(self):
        server = await websockets.serve(self.handle_client, self.host, self.port)
        logger.info(f"WebSocket server started on ws://{self.host}:{self.port}")
        await server.wait_closed()


async def main():
    server = WebSocketServer()
    try:
        await server.start()
    except KeyboardInterrupt:
        logger.info("Server stopped")


if __name__ == "__main__":
    asyncio.run(main())