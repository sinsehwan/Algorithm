#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n;
vector<vector<int>> graph;
int answer = 0;

enum State { R, C, RC };

// BFS 탐색을 위한 큐 요소를 정의합니다.
struct Node {
    State st;
    int y, x;
};

queue<Node> q;

// 다음 위치를 계산하는 함수
pair<int, int> getEnd(State st, int y, int x) {
    if (st == R) {
        return {y, x + 1};
    } else if (st == C) {
        return {y + 1, x};
    } else {
        return {y + 1, x + 1};
    }
}

// 끝에 도달했는지 확인하는 함수
bool isEnd(State st, int y, int x) {
    if (st == R && y == n - 1 && x == n - 2) {
        return true;
    } else if (st == C && y == n - 2 && x == n - 1) {
        return true;
    } else if (st == RC && y == n - 2 && x == n - 2) {
        return true;
    } else {
        return false;
    }
}

// 새로운 상태와 위치가 유효한지 확인하는 함수
bool isPromising(State ns, int ny, int nx) {
    if (ns == R) {
        if (nx + 1 >= n || graph[ny][nx + 1] == 1) {
            return false;
        }
    } else if (ns == C) {
        if (ny + 1 >= n || graph[ny + 1][nx] == 1) {
            return false;
        }
    } else {
        if (ny + 1 >= n || nx + 1 >= n || graph[ny][nx + 1] == 1 || graph[ny + 1][nx] == 1 || graph[ny + 1][nx + 1] == 1) {
            return false;
        }
    }
    return true;
}

// BFS 함수
void bfs() {
    while (!q.empty()) {
        int qSize = q.size();
        for (int i = 0; i < qSize; i++) {
            Node node = q.front();
            q.pop();

            auto [ny, nx] = getEnd(node.st, node.y, node.x);

            if (isEnd(node.st, node.y, node.x)) {
                answer++;
            } else {
                if (node.st == RC) {
                    if (isPromising(R, ny, nx)) q.push({R, ny, nx});
                    if (isPromising(C, ny, nx)) q.push({C, ny, nx});
                    if (isPromising(RC, ny, nx)) q.push({RC, ny, nx});
                } else if (node.st == R) {
                    if (isPromising(R, ny, nx)) q.push({R, ny, nx});
                    if (isPromising(RC, ny, nx)) q.push({RC, ny, nx});
                } else {
                    if (isPromising(C, ny, nx)) q.push({C, ny, nx});
                    if (isPromising(RC, ny, nx)) q.push({RC, ny, nx});
                }
            }
        }
    }
}

int main() {
    cin >> n;
    graph.resize(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }

    if (graph[n - 1][n - 1] == 1) {
        cout << 0 << endl;
    } else {
        q.push({R, 0, 0});
        bfs();
        cout << answer << endl;
    }

    return 0;
}
