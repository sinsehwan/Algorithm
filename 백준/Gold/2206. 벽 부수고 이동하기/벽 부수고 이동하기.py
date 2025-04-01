import sys
from collections import deque

input = sys.stdin.readline

n, m = list(map(int, input().split()))

graph = [list(map(int, input().strip())) for _ in range(n)]

visited = [[[0] * 2 for _ in range(m)] for _ in range(n)]

vec = [[-1, 0], [1, 0], [0, -1], [0, 1]]

queue = deque([(0, 0, 1, 1)])

def bfs():
    while queue:
        size = len(queue)
        for _ in range(size):
            y, x, life, dis = queue.popleft()

            if y == n - 1 and x == m - 1:
                return dis
            for v in vec:
                ny = y + v[0]
                nx = x + v[1]
                if isPromising(ny, nx, life):
                    if graph[ny][nx] == 1 and life == 1:
                        queue.append((ny, nx, life - 1, dis + 1))
                        visited[ny][nx][life - 1] = 1
                    elif graph[ny][nx] == 0:
                        queue.append((ny, nx, life, dis + 1))
                        visited[ny][nx][life] = 1

    return -1

def isPromising(y, x, life):
    if y < 0 or y >= n or x < 0 or x >= m:
        return False
    elif visited[y][x][life] == 1:
        return False
    else:
        return True
    
print(bfs())

