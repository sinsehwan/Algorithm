import sys

sys.setrecursionlimit(10**6)

input = sys.stdin.readline

n, r, q = map(int, input().split())

graph = [[] for i in range(n + 1)]
parent = [-1 for i in range(n + 1)]
child = [[] for i in range(n + 1)]

size = [0] * (n + 1)

for _ in range(n - 1):
    a, b = map(int, input().split())

    graph[a].append(b)
    graph[b].append(a)

queries = []

for _ in range(q):
    queries.append(int(input()))

def makeTree(currentNode, parentNode):
    for node in graph[currentNode]:
        if node != parentNode:
            parent[node] = currentNode
            child[currentNode].append(node)
            makeTree(node, currentNode)

def countSubtreeNodes(currentNode):
    size[currentNode] = 1

    for node in child[currentNode]:
        countSubtreeNodes(node)
        size[currentNode] += size[node]

makeTree(r, -1)
countSubtreeNodes(r)

for q in queries:
    print(size[q])