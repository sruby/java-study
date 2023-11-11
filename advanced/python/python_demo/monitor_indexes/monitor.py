import yfinance as yf
import time
import notification

# Download historical data as a Pandas DataFrame
data = yf.download('000300.SS', start='2023-01-01', end='2023-12-31')

import pandas as pd

# Convert the index to a DatetimeIndex
data.index = pd.to_datetime(data.index)

# Filter the data for the last 3 years
data_last_3_years = data.loc[data.index.year >= 2021]

# Find the lowest price in the "Low" column
lowest_3_years = data_last_3_years['Low'].min()

while True:
    # Fetch the latest data
    latest_data = yf.download('INDEX_NAME', period='1d')

    # Check if we've hit a new low
    if latest_data['Low'].min() <= lowest_3_years:
        print('Index hit a new 3-year low')
    # if latest_data['Low'].min() <= lowest_5_years:
    #     print('Index hit a new 5-year low')

    # Sleep for a day
    time.sleep(24 * 60 * 60)


# import sendgrid
# from sendgrid.helpers.mail import *
#
# def send_email(subject):
#     sg = sendgrid.SendGridAPIClient(api_key='SENDGRID_API_KEY')
#
#     from_email = Email("test@example.com")
#     to_email = To("test@example.com")
#     content = Content("text/plain", subject)
#     mail = Mail(from_email, to_email, subject, content)
#
#     response = sg.client.mail.send.post(request_body=mail.get())
#
# # Send an email when a new low is hit
# if latest_data['Low'].min() <= lowest_3_years:
#     send_email('Index hit a new 3-year low')
# if latest_data['Low'].min() <= lowest_5_years:
#     send_email('Index hit a new 5-year low')