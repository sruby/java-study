import pyautogui

#截图保存并根据图片点击
# part_screenshot = pyautogui.screenshot(region=(0,0,1030,120))
# part_screenshot.save('part_screenshot.png');
locate_on_screen_part = pyautogui.locateOnScreen('part_screenshot.png')
if locate_on_screen_part is not None:
    left, top, width, height = locate_on_screen_part
    click = pyautogui.click(width,height)
else:
    print("not found on the screen.")