import sys

input = sys.stdin.readline

n, m = list(map(int, input().split()))

book = dict()

for i in range(1, n + 1):
    el = input().strip()
    book[str(i)] = el
    book[el] = i

for i in range(m):
    el = input().strip()
    print(book[el])
