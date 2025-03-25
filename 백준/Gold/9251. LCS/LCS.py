import sys

input = sys.stdin.readline

listA = list(input().strip())

listB = list(input().strip())

check = [[0] * (len(listB) + 1) for _ in range(len(listA) + 1)]

answer = 0
for i in range(len(listA)):
    for j in range(len(listB)):
        if listA[i] == listB[j]:
            check[i + 1][j + 1] = check[i][j] + 1

        else:
            check[i + 1][j + 1] = max(check[i][j + 1], check[i + 1][j])

        if answer < check[i + 1][j + 1]:
            answer = check[i + 1][j + 1]

print(answer)
