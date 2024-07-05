import asyncio
import websockets

class DataReceiver:
    def __init__(self, uri):
        self.uri = uri
        self.data = []

    async def receive_data(self):
        print("receive_data")
        async with websockets.connect(self.uri) as websocket:
            while True:
                data = await websocket.recv()
                self.data.append(data)
                print(f"接收到数据: {data}")

    def get_data(self):
        return self.data