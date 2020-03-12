import queue


def isInside(c, N):
    return 1 <= c[0] <= N and 1 <= c[1] <= N


def min_knight_steps(N, kpos, tpos):
    dx = [-2, 2, 2,   1, -1, 1, -2, -1]
    dy = [-1, 1, -1, -2, -2,  2,  1, 2]
    q = queue.Queue()
    q.put([kpos, 0])
    visited = {}
    for i in range(1,N+1):
        for j in range(1,N+1):
            visited[str(i) + ":" + str(j)] = False
    visited[str(kpos[0]) + ":" + str(kpos[1])] = True
    while not q.empty():
        node = q.get()
        if (node[0])[0] == tpos[0] and (node[0])[1] == tpos[1]:
            return node[1]
        for i in range(8):
            x = node[0][0] + dx[i]
            y = node[0][1] + dy[i]
            if isInside((x, y), N) and not visited[str(x) + ":" + str(y)]:
                visited[str(x) + ":" + str(y)] = True
                q.put([(x, y), node[1] + 1])


t = int(input())
for x in range(t):
    n = int(input())
    kx, ky = map(int,input().strip(" ").split(" "))
    tx, ty = map(int,input().strip(" ").split(" "))
    print(min_knight_steps(n, (kx, ky), (tx, ty)))
