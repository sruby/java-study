def test():
    # list
    l = [1,2,'a',3]
    # print(l[0])
    # print(l[1:2])

    # a list of lists,also known as a 2D list or matrix.
    list_of_lists = [
        [1, 2],
        [3, 4]
    ]
    # print(list_of_lists[0][0])
    # Iterative output of a 2D list
    for row in list_of_lists:
        for item in row:
            print(item)

    # tuple
    tup = (1,2,'a',3,'b')
    # print(tup[1])
    #
    # print(tup[-1])

    # print(tup[5]) # tuple index out of range

test()