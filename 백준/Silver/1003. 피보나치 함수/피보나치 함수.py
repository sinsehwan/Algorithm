import sys

input = sys.stdin.readline

case = int(input())

fibList = [[0] * 2 for i in range(41)]
#print(fibList)

fibList[0] = [1, 0]
fibList[1] = [0, 1]

for i in range(2, 41):
    fibList[i][0] = fibList[i - 1][0] + fibList[i - 2][0]
    fibList[i][1] = fibList[i - 1][1] + fibList[i - 2][1]

for i in range(case):
    num = int(input())

    a, b = fibList[num]

    print(str(a) + " " + str(b))