n, k = list(map(int, input().split()))

def fac(input):
    result = 1

    for i in range(2, input + 1):
        result *= i

    return result

print(fac(n) // (fac(n-k) * fac(k)))