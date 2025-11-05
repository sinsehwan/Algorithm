import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    char[][] graph = new char[3][3];

    int xCount = 0;
    int oCount = 0;
    int[][][] pattern = {{{0, 0}, {0, 1}, {0, 2}}, {{1, 0}, {1, 1}, {1, 2}}, {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}}, {{0, 1}, {1, 1}, {2, 1}}, {{0, 2}, {1, 2}, {2, 2}},
            {{0, 0}, {1, 1}, {2, 2}}, {{0, 2}, {1, 1}, {2, 0}}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    public void solve() throws IOException {

        while (getInput() != -1) {
            if (isValid()) {
                bw.write("valid\n");
            }
            else {
                bw.write("invalid\n");
            }
        }
    }

    int getInput() throws IOException {
        String str = br.readLine();

        if ("end".equals(str)) {
            return -1;
        }

        graph = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                graph[i][j] = str.charAt(i * 3 + j);
            }
        }

        return 0;
    }

    boolean isValid() throws IOException {
        if (!isValidTurn()) return false;

        if (isFull() && !xWin() && !oWin()) return true;
        int dif = xCount - oCount;
        if((dif == 1 && xWin() && !oWin()) || (dif == 0 && oWin() && !xWin())) {
            return true;
        }

        return false;
    }

    boolean xWin() {
        for (int i = 0; i < 8; i++) {
            if (isEndPattern(graph, i, 'X')) {
                return true;
            }
        }

        return false;
    }

    boolean oWin() {
        for (int i = 0; i < 8; i++) {
            if (isEndPattern(graph, i, 'O')) {
                return true;
            }
        }

        return false;
    }

    boolean isFull() {
        if (xCount - oCount != 1) {
            return false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (graph[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isValidTurn() {
        xCount = 0;
        oCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (graph[i][j] == 'X') {
                    xCount += 1;
                }
                else if (graph[i][j] == 'O') {
                    oCount += 1;
                }
            }
        }

        return xCount - oCount == 0 || xCount - oCount == 1;
    }

    boolean isEndPattern(char[][] graph, int pIdx, char c) {
        for (int i = 0; i < 3; i++) {
            char nc = graph[pattern[pIdx][i][0]][pattern[pIdx][i][1]];

            if (c != nc) {
                return false;
            }
        }

        return true;
    }

    // valid 조건
    /**
     * X개수가 O보다 1개 더 많거나 같아야 함.
     * 꽉 차는 경우 제외 (단, 이 경우 x개수가 1개 더 많아야 하고, 승자가 없어야 한다.)
     * x가 더 많다면 x가 이겨야 함
     * o개수 같으면 o가 이겨야 함
     * 가로 3개 세로 3개 대각선 2개
     */
}
