import sys
from collections import deque

n, m = map(int, input().split())

graph = []
adjacentNum = [[0] * m for _ in range(n)]
groupNum = [[0] * m for _ in range(n)]
pgNum = 0

for _ in range(n):
    line = [int(item) for item in input().strip()]
    graph.append(line)

vec = [[-1, 0], [1, 0], [0, -1], [0, 1]]

queue = deque()

def bfs(y, x):
    global adjacentNum, groupNum, pgNum
    if graph[y][x] == 1 or adjacentNum[y][x] != 0:
        return
    
    cnt = 1
    pgNum += 1
    
    queue.append([y, x])

    visited = set()
    visited.add((y, x))

    while queue:
        py, px = queue.popleft()

        for v in vec:
            ny, nx = py + v[0], px + v[1]

            if isPromising(ny, nx) and (ny, nx) not in visited and graph[ny][nx] == 0:
                visited.add((ny, nx))
                queue.append([ny, nx])
                cnt += 1
    
    for item in visited:
        ty, tx = item
        adjacentNum[ty][tx] = cnt
        groupNum[ty][tx] = pgNum

def isPromising(y, x):
    if y < 0 or y >= n or x < 0 or x >= m:
        return False
    return True

for i in range(n):
    for j in range(m):
        bfs(i, j)

for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            print(0, end="")
        else:
            addedGroupList = []
            result = 1
            for v in vec:
                ay, ax = i + v[0], j + v[1]
                if isPromising(ay, ax) and groupNum[ay][ax] not in addedGroupList:
                    result += adjacentNum[ay][ax]
                    addedGroupList.append(groupNum[ay][ax])
            print(result % 10, end="")
    print()
