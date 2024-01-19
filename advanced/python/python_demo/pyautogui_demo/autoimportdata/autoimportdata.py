import pyautogui
import time
import logging
import requests
from subprocess import Popen
import pygetwindow as gw

# Configure logging
log_file_path = "C:\\sci992_log\\logfile.log"
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler(log_file_path),
        logging.StreamHandler()  # This adds the console handler
    ]
)

# Open Excel file using Popen
def open_excel(file_path):
    try:
        Popen(["start", "excel.exe", file_path], shell=True)
        logging.info("Excel opened")
    except Exception as e:
        logging.error(f"Failed to open Excel: {e}")

# Send a GET request using requests library
def import_data(url):
    try:
        response = requests.get(url)
        response.raise_for_status()  # Raises an HTTPError if the HTTP request returned an unsuccessful status code
        logging.info(f"Data import successful, status code: {response.status_code}")
    except requests.exceptions.RequestException as e:
        logging.error(f"Error during HTTP request: {e}")

# Main script execution
logging.info("0 - Script started")
excel_file_path = "C:\\sci992\\ALL.xlsx"

open_excel(excel_file_path)
# time.sleep(10)
time.sleep(180)

# Code to check the result and decide if Excel should be closed goes here
try:
    window2 = gw.getWindowsWithTitle("提示")[0]
    window2.activate()
    logging.info("Confirmation box activated")
    try:
        pyautogui.press('esc')
        # pyautogui.hotkey('alt', '4')
        logging.info("Confirmation box closed")
    except Exception as e:
        logging.error(f"Failed to close the confirmation box: {e}")

    # Activate Excel window
    window = gw.getWindowsWithTitle("ALL")[0]
    window.activate()
    logging.info("Excel activated")

    # Press the escape key to close the confirmation box
    # time.sleep(3)
    # try:
    #     pyautogui.press('esc')
    #     pyautogui.hotkey('alt', '4')
    #     logging.info("Confirmation box closed")
    # except Exception as e:
    #     logging.error(f"Failed to close the confirmation box: {e}")

    time.sleep(3)
    try:
        # Send Ctrl+S to save
        pyautogui.hotkey('ctrl', 's')
        logging.info("Save command sent")
    except Exception as e:
        logging.error(f"Failed to save the Excel file: {e}")

    time.sleep(5)
    logging.info("Waited for 5 seconds after save")

    try:
        # Close Excel window
        window.close()
        logging.info("Excel window closed")

        time.sleep(3)
        import_data_url = "http://127.0.0.1:8101/sci992/import?whetherCheck"
        logging.info("Attempting to execute import data command")
        # import_data(import_data_url)
    except Exception as e:
        logging.error(f"Failed to close the Excel window: {e}")

except IndexError:
    logging.error("Excel window with title 'ALL' was not found.")
except Exception as e:
    logging.error(f"An unexpected error occurred: {e}")


