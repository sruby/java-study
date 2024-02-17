import sys
import time
import logging
from subprocess import Popen
from pywinauto import Application, Desktop
import requests
import psutil

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

def is_excel_open(file_name):
    # Check if there's any Excel process running
    for proc in psutil.process_iter(['pid', 'name']):
        if proc.info['name'] == 'EXCEL.EXE':
            # If Excel is running, check if the specific file is open
            try:
                app = Application(backend="uia").connect(process=proc.pid)
                if app.windows()[0].window_text().startswith(file_name):
                    return True
            # catch all exception
            except Exception as e:
                # Process does not exist or window not found
                logging.error(f"Error: {e}")
                pass
    return False


# Open Excel file using Popen
def open_excel(file_path):
    Popen(["start", "excel.exe", file_path], shell=True)
    logging.info("Excel opened")


# Send a GET request using requests library
def import_data(url):
    try:
        response = requests.get(url)
        response.raise_for_status()  # Raises an HTTPError if the HTTP request returned an unsuccessful status code
        logging.info(f"Data import successful, status code: {response.status_code},message: {response.text}")
    except requests.exceptions.RequestException as e:
        logging.error(f"Error during HTTP request: {e}")

# Main script execution
logging.info("0 - Script started")
excel_file_name = "ALL.xlsx"
excel_file_path = f"C:\\sci992\\{excel_file_name}"

# Check if Excel with ALL.xlsx is already open
if is_excel_open(excel_file_name):
    logging.info(f"{excel_file_name} is already open.")
    sys.exit()
else:
    open_excel(excel_file_path)
    # Rest of the script...
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
