import pandas as pd

def tracking_error( data):
    df = pd.DataFrame(data)

    # Calculate the difference in returns
    df['return_diff'] = df['portfolio_returns'] - df['benchmark_returns']

    # Calculate Tracking Error as the annualized standard deviation of the return differences
    tracking_error = df['return_diff'].std() * (252 ** 0.5)  # assuming 252 trading days in a year, 252 ** 0.5 calculates the square root of 252

    print(f"Tracking Error: {tracking_error}")

# Assuming we have a DataFrame with portfolio returns and benchmark returns
data = {
    'portfolio_returns': [0.02, 0.01, 0.03, -0.01, 0.00],
    'benchmark_returns': [0.02, 0.02, 0.02, 0.00, 0.01]
}
# 0.14198591479439077
tracking_error(data)