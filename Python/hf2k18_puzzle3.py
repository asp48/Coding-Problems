import math

square_table = {}
happyTable = {}
primeList = [True for i in range(12000000 + 1)]

def init():
    global square_table
    for i in range(0, 10, 1):
        square_table[i] = i ** 2


def happy(n):
    res = happyTable.get(n, "")
    if res is True:
        return True
    elif res is False:
        return False
    past = set()
    givenNumber = n
    while n != 1:
        n = sum(square_table[int(i)] for i in str(n))
        res = happyTable.get(n, "")
        if res:
            break
        elif not res:
            for p in past:
                happyTable[p] = False
        if n == 4:
            for p in past:
                happyTable[p] = False
            happyTable[givenNumber] = False
            return False
        if n in past:
            for p in past:
                happyTable[p] = False
            happyTable[givenNumber] = False
            return False
        past.add(n)
    for p in past:
        happyTable[p] = True
    happyTable[n] = True
    return True


def SieveOfEratosthenes(n):
    global primeList
    p = 2
    while p * p <= n:

        # If prime[p] is not changed, then it is a prime
        if (primeList[p] == True):

            # Update all multiples of p
            for i in range(p * 2, n + 1, p):
                primeList[i] = False
        p += 1


def prime(num):
    return primeList[num]

def checkPrime(num):
    snum = str(num)
    lastDigit = int(snum[len(snum) - 1:])
    if lastDigit in [0,2,4,6,8]:
        return False
    limit = int(math.sqrt(num))
    limit += 2
    for i in range(2, limit, 1):
        if (num % i) == 0:
            return False
    return True


init()
SieveOfEratosthenes(12000000)
happyPrimeList = []
for x in range(2, 12000000, 1):
    if prime(x) and happy(x):
        happyPrimeList.append(x)
print(len(happyPrimeList))
prev = 0
largest = 0
cumPrimeCount = 0
totalSum = 0
for i in range(0,100000,1):
    totalSum += happyPrimeList[i]
i = 99999
while i >= 0:
    print("Computing ", i)
    if checkPrime(totalSum):
        cumPrimeCount += 1
        largest = totalSum
        break
    totalSum -= happyPrimeList[i]
    i -= 1

print(cumPrimeCount)
print(largest)
