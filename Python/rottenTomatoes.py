import queue

def isInside(x, y, r, c):
    return 0 <= x < r and 0 <= y < c


def timeToRot(A, r, c):
    q = queue.Queue()
    dx = [0, 0, -1, 1]
    dy = [-1, 1, 0, 0]
    ans = 0

    for i in range(r):
        for j in range(c):
            if A[i][j] == 2:
                q.put((i, j))
    q.put((-1 ,-1))
    t = 0
    while not q.empty():
        changed = False
        node = q.get()
        while node[0] != -1 and node[1] != -1:
            for i in range(4):
                x = node[0] + dx[i]
                y = node[1] + dy[i]
                if isInside(x, y, r, c) and A[x][y] == 1:
                    if not changed:
                        ans += 1
                        changed = True
                    A[x][y] = 2
                    q.put((x, y))
            node = q.get()

        if not q.empty():
            q.put((-1, -1))

    for m in range(r):
        for n in range(c):
            if A[m][n] == 1:
                return -1
    return ans


tc = int(input())
for t in range(tc):
    r, c = input().strip(" ").split(" ")
    r, c = int(r), int(c)
    iput = [int(i) for i in input().strip(" ").split(" ") if i.isdigit()]
    A = []
    k = 0
    for i in range(r):
        row = []
        for j in range(c):
            row.append(iput[k])
            k += 1
        A.append(row)
    print(timeToRot(A, r, c))
