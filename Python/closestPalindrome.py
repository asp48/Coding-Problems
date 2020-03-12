
def isPalindrome(n_str):
    length = len(n_str)
    for i in range(int(length/2)):
        if n_str[i] != n_str[length-i-1]:
            return False
    return True

def carry(n_str,carry,size):
    str_list = list(n_str)
    if carry == -1:
        i = size -1
        while i>=0 and str_list[i] == '0':
            str_list[i] = '9'
            i -= 1
        if i >= 0:
            str_list[i] = str(int(str_list[i])-1)
    else:
        for i in range(size-1,-1,-1):
            digit = int(str_list[i]) + carry
            str_list[i] = str(int(digit % 10))
            carry = digit/10
    return "".join(str_list)

def getClosesePalindrome(n):
    n_str = str(n)
    if isPalindrome(n_str):
        return n
    length = len(n_str)
    nine_str = "9"*length
    if n_str == nine_str:
        return n+2
    neighbours = []
    middleIndex = int(length / 2)
    FH = n_str[0:middleIndex]
    SH = FH[::-1]
    fh_len = len(FH)
    if length%2 == 0:
        palnum = FH + SH
        neighbours.append(int(palnum))
        if FH[fh_len-1] == '0':
            tempFH = carry(FH,-1,fh_len)
        else:
            tempFH = FH[0:fh_len-1] + str(int(FH[fh_len-1])-1)
        palnum = tempFH + tempFH[::-1]
        neighbours.append(int(palnum))
        if FH[fh_len-1] == '9':
            tempFH = carry(FH,1,fh_len)
        else:
            tempFH = FH[0:fh_len-1] + str(int(FH[fh_len-1])+1)
        palnum = tempFH + tempFH[::-1]
        neighbours.append(int(palnum))
    else:
        palnum = FH + n_str[middleIndex] + SH
        neighbours.append(int(palnum))
        if n_str[middleIndex] == '0':
            tempFH = carry(FH,-1,fh_len)
            palnum = tempFH + "9" + tempFH[::-1]
        else:
            palnum = FH + str(int(n_str[middleIndex])-1) + SH
        neighbours.append(int(palnum))
        if n_str[middleIndex] == '9':
            tempFH = carry(FH,1,fh_len)
            palnum = tempFH + "0" + tempFH[::-1]
        else:
            palnum = FH + str(int(n_str[middleIndex])+1) + SH
        neighbours.append(int(palnum))


    min_diff = 9999999999999999999999
    ans = 0
    neighbours.sort()
    for num in neighbours:
        if abs(n-num) < min_diff:
           min_diff = abs(n-num)
           ans = num
    return ans


t = int(input())
for i in range(t):
    n = int(input())
    print(getClosesePalindrome(n))
