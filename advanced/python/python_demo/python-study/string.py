name = 'jason'
city = 'beijing'
text = "welcome to jike shijian"

print(name)
print(city)
print(text)

s1 = 'hello'
s2 = "hello"
s3 = """hello"""
# True
print(s1 == s2 == s3)


s = 'a\nb\tc'
print(s)


name = 'json'
print(name[0])
print(name[0:3])

for char in name:
    print(char)

# name[0] = 'h' 'str' object does not support item assignment

s = 'hson'
s = 'H' + s[1:]
print(s)
s = s.replace('h', 'H')
print(s)