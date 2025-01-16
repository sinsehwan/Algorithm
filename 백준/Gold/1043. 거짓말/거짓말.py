import sys

input = sys.stdin.readline

n, m = map(int, input().split())

truthList = list(map(int, input().split()))
parent = [i for i in range(n + 1)]
node_rank = [1] * (n + 1)

party = []

for _ in range(m):
    party.append(list(map(int, input().split())))

def find_root(x):
    if x == parent[x]:
        return x
    else:
        parent[x] = find_root(parent[x])
        return parent[x]

def union_root(x, y):
    x = find_root(x)
    y = find_root(y)

    if x != y:
        if node_rank[x] > node_rank[y]:
            parent[y] = x
        elif node_rank[x] < node_rank[y]:
            parent[x] = y
        else:
            parent[x] = y
            node_rank[x] += 1

node_rank[0] = 51
for i in range(1, len(truthList)):
    union_root(0, truthList[i])

for p in party:
    for i in range(1, len(p) - 1):
        #print(p[i], p[i + 1])
        union_root(p[i], p[i + 1])

count = 0
for p in party:
    flag = False
    for i in range(1, len(p)):
        if find_root(p[i]) == 0:
            flag = True
            break
    if flag == False:
        #print(p)
        count += 1

#for item in parent:
#    print(item, end=" ")

print(count)


    




