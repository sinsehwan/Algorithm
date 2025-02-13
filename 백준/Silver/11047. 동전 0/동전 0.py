import sys

input = sys.stdin.readline

n, k = list(map(int, input().split()))

coinCount = 0

coinList = [0] * n

for i in range(n):
    coinList[i] = int(input())

for item in reversed(coinList):
    if k >= item:
        coins = k // item
        coinCount += coins
        k -= coins * item

print(coinCount)