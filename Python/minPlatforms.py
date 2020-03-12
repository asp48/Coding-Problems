t = int(input())
for x in range(t):
    n = int(input())
    A = [int(i) for i in input().strip(" ").split(" ") if i.isdigit()]
    D = [int(i) for i in input().strip(" ").split(" ") if i.isdigit()]
    platforms_needed = 1
    result = 1
    ''' Merge Sort Solution
    A.sort()
    D.sort()
    i = 1
    j = 0
    while i < n and j < n:
        if A[i] < D[j]:
            platforms_needed += 1
            i += 1
            result = max(platforms_needed,result)
        else:
            platforms_needed -= 1
            j += 1
    print(result)
    '''
    sorted_arr = []
    for i in range(n):
        sorted_arr.append(['a',A[i]])
        sorted_arr.append(['d', D[i]])
    sorted_arr.sort(key=lambda e:e[1])
    for i in range(2*n):
        if sorted_arr[i][0] == 'a':
            platforms_needed += 1
            result = max(platforms_needed, result)
        else:
            platforms_needed -= 1

    print(result)

