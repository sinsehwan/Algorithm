import sys

input = sys.stdin.readline

data = list(map(int, input().split()))

dp = [[[[-1] * 51 for _ in range(51)] for _ in range(51)] for _ in range(51)]

mod = 1000000007

def solve(s, a, b, c):
    if s == 0:
        if a == 0 and b == 0 and c == 0:
            return 1
        else:
            return 0
    
    if a < 0 or b < 0 or c < 0:
        return 0

    if dp[s][a][b][c] != -1:
        return dp[s][a][b][c]
    
    result = 0
    result += solve(s - 1, a - 1, b, c)
    result += solve(s - 1, a, b - 1, c)
    result += solve(s - 1, a, b, c - 1)
    result += solve(s - 1, a - 1, b - 1, c)
    result += solve(s - 1, a - 1, b, c - 1)
    result += solve(s - 1, a, b - 1, c - 1)
    result += solve(s - 1, a - 1, b - 1, c - 1)
    result %= mod
    dp[s][a][b][c] = result
    return result

print(solve(data[0], data[1], data[2], data[3]))