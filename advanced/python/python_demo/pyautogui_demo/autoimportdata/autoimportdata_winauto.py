import time
import logging
from subprocess import Popen
from pywinauto import Application, Desktop
import requests

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
# time.sleep(30)  # Give time for Excel to open
logging.info("waiting for confirmation box activated")

# Connect to the Excel application
app = Application(backend="uia").connect(path="excel.exe")

# Access Excel's main window
main_window = app.window(title_re=".*Excel.*")

# Wait for the confirmation dialog to appear and interact with it
# Assuming 'main_window' is the parent window
confirmation_dialog = main_window.child_window(title="提示")
confirmation_dialog.wait('visible', timeout=240)  # Wait for up to 240 seconds for the dialog
logging.info("Confirmation box activated")

# Press 'ESC' button on the confirmation dialog
confirmation_dialog.type_keys('{ESC}', with_spaces=True)
logging.info("Confirmation box closed")

# Make sure the main Excel window is active
main_window.set_focus()
logging.info("Excel activated")

time.sleep(2)
# Send Ctrl+S to save
main_window.type_keys('^s', with_spaces=True)
logging.info("Save command sent")

time.sleep(2)
logging.info("Waited for 5 seconds after save")

# Close Excel window
main_window.close()
logging.info("Excel window closed")

time.sleep(3)
import_data_url = "http://127.0.0.1:8101/sci992/import?whetherCheck"
logging.info("Attempting to execute import data command")
import_data(import_data_url)