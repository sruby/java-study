class A:
    def __init__(self):
        print("Constructor of class A")

    def method(self):
        print("Method in class A")

class B(A):
    def __init__(self):
        super().__init__()
        print("Constructor of class B")

    def method(self):
        print("Method in class B")
        super().method()

class C(A):
    def __init__(self):
        super().__init__()
        print("Constructor of class C")

    def method(self):
        print("Method in class C")
        super().method()

class D(B, C):
    def __init__(self):
        super().__init__()
        print("Constructor of class D")

    def method(self):
        print("Method in class D")
        super().method()

# Create an instance of class D
d_instance = D()
d_instance.method()

# Display the Method Resolution Order
print(D.mro())