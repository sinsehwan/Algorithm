import sys
from collections import deque

input = sys.stdin.readline


inputList = list(input().strip())

explosion = list(input().strip())

stack = deque()

for i in range(len(inputList)):
    stack.append(inputList[i])
    #print(stack)

    if len(stack) >= len(explosion):
        check = True
        for j in range(len(explosion)):
            if stack[len(stack) - len(explosion) + j] != explosion[j]:
                check = False
                break
        if check:
            for j in range(len(explosion)):
                stack.pop()

if len(stack) == 0:
    print("FRULA")
else:
    for item in stack:
        print(item, end="")

