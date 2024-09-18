# 공통 부분 문자열 골5

import sys

input = sys.stdin.readline

str1 = input().strip()
str2 = input().strip()

graph = [[0] * (len(str2) + 1) for _ in range(len(str1) + 1)]

answer = 0

for i in range(1, len(str1) + 1):
    for j in range(1, len(str2) + 1):
        if str1[i - 1] == str2[j - 1]:
            graph[i][j] = graph[i - 1][j - 1] + 1
            if answer < graph[i][j]:
                answer = graph[i][j]
        else:
            graph[i][j] = 0

print(answer)

#print(graph)