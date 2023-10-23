import pyautogui

screenWidth, screenHeight = pyautogui.size()
print("screenWidth：{},screenHeight:{}".format(screenWidth,screenHeight))

currentMouseX, currentMouseY = pyautogui.position()
print("currentMouseX：{},currentMouseY:{}".format(currentMouseX,currentMouseY))

# pyautogui.moveTo(1030,120)
#
# pyautogui.click()

# pyautogui.click('button.png')

screenshot = pyautogui.screenshot()

locate_on_screen = pyautogui.locateOnScreen('head.png')
if locate_on_screen is not None:
    click = pyautogui.click('head.png')
else:
    print("head not found on the screen.")







