import sys

input = sys.stdin.readline

n, m = list(map(int, input().split()))

numList = list(map(int, input().split()))

sectionSum = [0]*(len(numList) + 1)

sectionSum[1] = numList[0]

for i in range(2, len(numList) + 1):
    sectionSum[i] = sectionSum[i - 1] + numList[i - 1]

for k in range(m):
    i, j = list(map(int, input().split()))
    result = sectionSum[j] - sectionSum[i - 1]

    print(result) 