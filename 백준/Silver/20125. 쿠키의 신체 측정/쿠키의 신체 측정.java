import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;

    char[][] graph;

    int hy;
    int hx;

    int[][] dir = {{0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        findHead();

        int hty = hy + 1;
        int htx = hx;

        int ay = hty + 1;
        int ax = htx + 1;
        bw.write(ay + " " + ax + "\n");

        int dLeft = calLength(hty, htx, 0);

        int dRight = calLength(hty, htx, 1);

        int dBody = calLength(hty, htx, 2);

        int dLeg1 = calLength(hty + dBody + 1, htx - 1, 2) + 1;
        int dLeg2 = calLength(hty + dBody + 1, htx + 1, 2) + 1;

        bw.write(dLeft + " " + dRight + " " + dBody + " " + dLeg1 + " " + dLeg2);
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        graph = new char[n][n];

        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
        }
    }

    void findHead() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == '*') {
                    hy = i;
                    hx = j;
                    return;
                }
            }
        }
    }

    int calLength(int sy, int sx, int dIdx) {
        int py = sy;
        int px = sx;
        while (isPromising(sy, sx) && graph[sy][sx] == '*') {
            sy += dir[dIdx][0];
            sx += dir[dIdx][1];
        }

        return Math.abs(sy - py) + Math.abs(sx - px) - 1;
    }

    boolean isPromising(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}
