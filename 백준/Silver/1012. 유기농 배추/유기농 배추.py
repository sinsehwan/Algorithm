import sys

input = sys.stdin.readline
graph = []
m = 0
n = 0
k = 0
# m -> 가로, n -> 세로 <= 50, k는 배추개수
def calEarthworm():
    global graph
    count = 0

    for i in range(n):
        for j in range(m):
            if graph[i][j] == 0:
                continue
            else:
                count += 1
                dfs(i, j)
    return count

vec = [[-1, 0], [1, 0], [0, 1], [0, -1]]

def dfs(n, m):
    global graph
    global k
    global vec

    if graph[n][m] == 0:
        return
    
    else:
        graph[n][m] = 0
        k -= 1
        for v in vec:
            if(promising(n + v[0], m + v[1])):
                dfs(n + v[0], m + v[1])
        
def promising(y, x):
    global n, m
    if y < 0 or y >= n or x < 0 or x >= m:
        return False
    else:
        return True

num = int(input())

for i in range(num):
    m, n, k = list(map(int, input().split()))
    graph = [[0] * m for i in range(n)]
    for i in range(k):
        x, y = list(map(int, input().split()))
        graph[y][x] = 1            
    result = calEarthworm()
    print(result)