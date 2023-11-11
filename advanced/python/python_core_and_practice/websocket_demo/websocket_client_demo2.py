import time
import websocket
import threading


def on_message(ws, message):
    print('Received: ' + message)


def on_open(ws):
    def gao():
        for i in range(5):
            time.sleep(0.01)
            msg = "{0}".format(i)
            ws.send(msg)
            print('Sent: ' + msg)

        time.sleep(1)

        ws.close()
        print("Websocket closed")

    print("start thread")

    # Create a new thread
    my_thread = threading.Thread(target=gao)

    # Start the thread
    my_thread.start()


try:
    ws = websocket.WebSocketApp("ws://localhost:8767/",
                                on_message=on_message,
                                on_open=on_open)
    ws.run_forever()
except Exception as e:
    print("WebSocket connection failed:", str(e))

# Sent: 0
# Sent: 1
# Received: 0
# Sent: 2
# Received: 1
# Sent: 3
# Received: 2
# Sent: 4
# Received: 3
# Received: 4
# Websocket
# closed
