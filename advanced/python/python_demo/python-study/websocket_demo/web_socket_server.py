# server.py
import asyncio
import websockets
import json

class WebSocketServer:
    def __init__(self, host="localhost", port=8765):
        self.host = host
        self.port = port

    async def handle_client(self, websocket, path):
        try:
            async for message in websocket:
                # Send 10 messages to the client
                # for i in range(1, 11):
                response = json.dumps({"status": "ok", "message": f"Server message : Responding to '{message}'"})
                await websocket.send(response)
                    # await asyncio.sleep(0.5)  # Short delay between messages
        except websockets.exceptions.ConnectionClosed:
            print("Client connection closed")

    async def start(self):
        server = await websockets.serve(self.handle_client, self.host, self.port)
        print(f"WebSocket server started on ws://{self.host}:{self.port}")
        await server.wait_closed()

if __name__ == "__main__":
    server = WebSocketServer()
    asyncio.run(server.start())