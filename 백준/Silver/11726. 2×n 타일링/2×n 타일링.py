import sys

input = sys.stdin.readline

n = int(input())

graph = [[0] * (i + 1) for i in range(n + 1)]

for i in range(1, n + 1):
    graph[i][0] = 1
    graph[i][i] = 1

for i in range(2, n + 1):
    for j in range(1, i):
        graph[i][j] = graph[i-1][j-1] + graph[i-1][j]

count = 0

for i in range((n // 2) + 1):
    count += graph[n - i][i]
    #print("graph " + str(n-i) + " " + str(i) + " : " + str(graph[n-i][i]))

print(count % 10007)