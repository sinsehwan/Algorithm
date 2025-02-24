import sys
import heapq
input = sys.stdin.readline

n = int(input())
m = int(input())

graph = [[] for _ in range(n + 1)]

distance = [sys.maxsize] * (n + 1)

for _ in range(m):
    st, end, price = list(map(int, input().split()))

    graph[st].append([end, price])

start, target = list(map(int, input().split()))

def get_smallest_node():
    min = sys.maxsize
    index = -1
    for i in range(1, len(distance)):
        if distance[i] < min:
            min = distance[i]
            index = i
    return index

def dijkstra():
    global start
    q = []
    heapq.heappush(q, [0, start])
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue
        
        for i in graph[now]:
            if distance[i[0]] > dist + i[1]:
                distance[i[0]] = dist + i[1]
                heapq.heappush(q, [distance[i[0]], i[0]])

dijkstra()


print(distance[target])