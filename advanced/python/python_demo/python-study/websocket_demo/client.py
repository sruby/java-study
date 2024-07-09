import asyncio
import websockets
import json


class WebSocketClient:
    def __init__(self, uri="ws://localhost:8765"):
        self.uri = uri
        self.websocket = None
        self.running = True
        self.send_queue = asyncio.Queue()

    async def connect(self):
        self.websocket = await websockets.connect(self.uri)
        print(f"Connected to {self.uri}")

    async def send_message(self, message):
        await self.send_queue.put(message)

    async def send_loop(self):
        while self.running:
            try:
                message = await self.send_queue.get()
                if self.websocket is None:
                    print("Not connected to a WebSocket server")
                    continue
                await self.websocket.send(message)
                print(f"Sent message: {message}")
            except Exception as e:
                print(f"Error sending message: {e}")

    async def listen_for_responses(self):
        while self.running:
            try:
                response = await self.websocket.recv()
                parsed_response = json.loads(response)
                print(f"Received response: {parsed_response}")
            except websockets.exceptions.ConnectionClosed:
                print("Connection to server closed")
                self.running = False
            except Exception as e:
                print(f"An error occurred while receiving: {e}")
        print("Stopped listening for responses")

    async def start(self):
        await self.connect()
        listen_task = asyncio.create_task(self.listen_for_responses())
        send_task = asyncio.create_task(self.send_loop())
        return listen_task, send_task

    async def stop(self):
        self.running = False
        if self.websocket:
            await self.websocket.close()


async def main():
    client = WebSocketClient()
    listen_task, send_task = await client.start()

    try:
        # Send first message
        await client.send_message("First message: Hello from client!")
        await asyncio.sleep(1)  # Short delay between messages

        # Send second message
        await client.send_message("Second message: This is a different content!")

        print("Messages sent. Continuing to listen for responses. Press Ctrl+C to exit.")

        # Wait for both tasks
        await asyncio.gather(listen_task, send_task)

    except KeyboardInterrupt:
        print("\nInterrupted by user. Shutting down...")
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        await client.stop()
        # Cancel both tasks
        listen_task.cancel()
        send_task.cancel()
        try:
            await listen_task
            await send_task
        except asyncio.CancelledError:
            pass


if __name__ == "__main__":
    asyncio.run(main())