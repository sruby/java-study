def test():
    # dict
    d1 = {'age':1,'name':'zhangsan'}
    print(d1['age'])
    print(d1['name'])

    d2 = dict({'age':1,'name':'zhangsan'})
    print(d2['age'])

    # set
    s1 = {1,2,'a',3}
    # print(s1[0]) 'set' object is not subscriptable
    s1.add("b")
    for item in s1:
        print(item)

test()

# 查找产品价格
def find_product_price(products, product_id):
    for id,price in products:
        if id == product_id:
            return price

    return None

# list
products = {
    (143121312,100),
    (432314553,30),
    (32421912367,150)
}

price_result = find_product_price(products, 12341234)

print('the price of product 12341234 is {}'.format(price_result))

# dict
products = {
  143121312: 100,
  432314553: 30,
  32421912367: 150
}
# 只需 O(1) 的时间复杂度就可以完成
print('The price of product 432314553 is {}'.format(products[432314553]))


# d = {'name': 'jason', ['education']: ['Tsinghua University', 'Stanford University']}
d = {'name': 'jason', ('education'): ['Tsinghua University', 'Stanford University']}
print(d[('education')])
