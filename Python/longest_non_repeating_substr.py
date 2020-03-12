def longestSubString(srcstr):
    n = len(srcstr)
    visited = [-1 for i in range(256)]
    cur_len = 1
    max_len = 1
    prev_index = 0
    visited[ord(srcstr[0])] = 0
    for i in range(1, n):
        prev_index = visited[ord(srcstr[i])]

        if prev_index == -1 or i - cur_len > prev_index:
            cur_len += 1
        else:
            max_len = max(cur_len, max_len)
            cur_len = i - prev_index

        visited[ord(srcstr[i])] = i
    return max(cur_len, max_len)


t = int(input())
for x in range(t):
    srcstr = input().strip(" ")
    print(longestSubString(srcstr))