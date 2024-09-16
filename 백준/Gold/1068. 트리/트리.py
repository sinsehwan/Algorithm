import sys

input = sys.stdin.readline

n = int(input())

parent = list(map(int, input().split()))

target = int(input())

child = [[] for _ in range(n)]

for i in range(n):
    if parent[i] != -1:
        child[parent[i]].append(i)

answer = 0

def findLeaf(root):
    if root == target:
        return
    
    checkNum = 0

    for ch in child[root]:
        if ch != target:
            checkNum += 1

    if checkNum > 0:
        for ch in child[root]:
            findLeaf(ch)
    else:
        global answer

        answer += 1

root = -1

for i in range(n):
    if parent[i] == -1:
        root = i

findLeaf(root)

print(answer)