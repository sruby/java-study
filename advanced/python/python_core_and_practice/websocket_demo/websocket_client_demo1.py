import asyncio
import websockets


async def hello():
    async with websockets.connect("ws://localhost:8767") as websocket:
        name = input("What's your name? ")
        await websocket.send(name)
        print(await websocket.recv())


asyncio.get_event_loop().run_until_complete(hello())
