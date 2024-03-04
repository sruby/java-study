# ValueError example - raised when a function receives an argument of the correct type but inappropriate value.
try:
    number = int("hello")  # This will raise a ValueError
except ValueError as e:
    print(e)

# User-defined exception example
class MyCustomError(Exception):
    pass

def do_something_bad():
    raise MyCustomError("Something bad happened!")

try:
    do_something_bad()
except MyCustomError as error:
    print(error)  # This will print: Something bad happened!