import sys

input = sys.stdin.readline

str1 = input().strip()
str2 = input().strip()

graph = [[0] * (len(str2) + 1) for _ in range(2)]
LCS = [[""] * (len(str2) + 1) for _ in range(2)]

maxLen = 0
maxStr = ""

for i in range(1, len(str1) + 1):
    for j in range(1, len(str2) + 1):
        if str1[i - 1] == str2[j - 1]:
            graph[1][j] = graph[0][j - 1] + 1
            LCS[1][j] = LCS[0][j - 1] + str1[i - 1]

            if graph[1][j] > maxLen:
                maxLen = graph[1][j]
                maxStr = LCS[1][j]
        else:
            graph[1][j] = max(graph[0][j], graph[1][j - 1])
            if graph[0][j] > graph[1][j - 1]:
                LCS[1][j] = LCS[0][j]
            else:
                LCS[1][j] = LCS[1][j - 1]
        
    for j in range(1, len(str2) + 1):
        graph[0][j] = graph[1][j]
        LCS[0][j] = LCS[1][j]

        graph[1][j] = 0
        LCS[1][j] = ""

print(maxLen)
if maxLen != 0:
    print(maxStr)