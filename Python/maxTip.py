t = int(input())
for i in range(t):
    N, X, Y = input().strip(" ").split(" ")
    N, X, Y = int(N), int(X), int(Y)
    A = [ int(i) for i in input().strip(" ").split(" ") ]
    B = [ int(i) for i in input().strip(" ").split(" ") ]
    tips = list(zip(A,B))
    tips.sort(key = lambda money: abs(money[0]-money[1]), reverse = True)
    cur = 0
    maxTip = 0
    while X >0 and Y > 0 and cur < N:
        if tips[cur][0] - tips[cur][1] > 0:
            maxTip += tips[cur][0]
            X -= 1
        else:
            maxTip += tips[cur][1]
            Y -= 1
        cur += 1
    while X > 0 and cur<N:
        maxTip += tips[cur][0]
        X -= 1
        cur += 1
    while Y > 0 and cur<N:
        maxTip += tips[cur][1]
        Y -= 1
        cur += 1
    print(maxTip)