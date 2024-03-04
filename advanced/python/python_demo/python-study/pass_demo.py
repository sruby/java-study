def my_function():
    print("Hello")
    pass
    print("World")


try:
    # Code that may raise an exception
    my_function()
except Exception:
    print("Something went")
    pass  # An exception might be logged or ignored if it's not significant.
    print("Something went wrong")

print("Done")