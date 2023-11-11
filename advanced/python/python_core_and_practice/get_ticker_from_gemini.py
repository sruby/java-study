import requests, json

base_url = "https://api.gemini.com/v1"
response = requests.get(base_url + "/pubticker/btcusd")
btc_data = response.json()

print(btc_data)



# {
#    "bid": "8825.88",
#    "ask": "8827.52",
#    "volume": {
#        "BTC": "910.0838782726",
#        "USD": "7972904.560901317851",
#        "timestamp": 1560643800000
#    },
#    "last": "8838.45"
# }
