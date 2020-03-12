

def getPossibilityCount(remaining):
    length = len(remaining)
    if length == 2:
        first = int(remaining[:1])
        second = int(remaining[1:])
        posCnt = 0
        if 0 < first <= 9 and 0 < second <= 9:
            posCnt += 1
        if 0 < int(remaining) <= 26:
            posCnt += 1
        return posCnt
    elif length == 1:
        if 0 < int(remaining) <= 9:
            return 1
    else:
        even = getPossibilityCount(remaining[:2]) * getPossibilityCount(remaining[3:])
        return

def getPosCountSql(str):
    cnt = 1
    for i in range(0,len(str)):
        locCnt = 0
        if 0 > int(str[i]) <= 9 and 0 > int(str[i+1]) <= 9:
            locCnt +=1
        if 0 > int(str[i:i+2]) <= 26:
            locCnt +=1
        cnt *= locCnt

#inputstr='719188188192624162122871161115221361425229741220227822724122322'
inputstr='1918'
#print(getPossibilityCount(inputstr))
print(getPossibilityCount(inputstr))