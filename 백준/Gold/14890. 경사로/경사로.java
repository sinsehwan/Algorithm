import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, l;
    int[][] graph;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (isValidCol(i)) {
                answer += 1;
            }
            if (isValidRow(i)) {
                answer += 1;
            }
        }

        bw.write(answer + "");
    }

    boolean isValidCol(int c) throws IOException {
        int[] line = new int[n];
        int[] lineReverse = new int[n];
        boolean[] isLadder = new boolean[n];
        boolean[] isLadderReverse = new boolean[n];

        for(int i = 0; i < n; i++) {
            line[i] = graph[i][c];
            lineReverse[i] = graph[n - 1 - i][c];
        }

        if(validateLine(line, isLadder)) {
            for(int i = 0; i < n; i++) {
                isLadderReverse[i] = isLadder[n - 1 - i];
            }
            return validateLine(lineReverse, isLadderReverse);
        }
        else {
            return false;
        }
    }

    boolean isValidRow(int r) throws IOException {
        int[] line = new int[n];
        int[] lineReverse = new int[n];
        boolean[] isLadder = new boolean[n];
        boolean[] isLadderReverse = new boolean[n];

        for (int i = 0; i < n; i++) {
            line[i] = graph[r][i];
            lineReverse[i] = graph[r][n - 1 - i];
        }
        if(validateLine(line, isLadder)) {
            for(int i = 0; i < n; i++) {
                isLadderReverse[i] = isLadder[n - 1 - i];
            }
            return validateLine(lineReverse, isLadderReverse);
        }
        else {
            return false;
        }
    }

    boolean validateLine(int[] line, boolean[] isLadder) throws IOException {
        int preHeight = line[0];

        for (int i = 1; i < n; i++) {
            int nextHeight = line[i];
            if (Math.abs(preHeight - nextHeight) > 1) return false;
            if (preHeight > nextHeight) {
                if (!canInstallLadder(line, i, isLadder)) return false;
                installLadder(i, isLadder);
            }

            preHeight = nextHeight;
        }

        return true;
    }

    boolean canInstallLadder(int[] line, int idx, boolean[] isLadder) throws IOException {
        int height = line[idx];

        for(int i = 0; i < l; i++) {
            int nIdx = idx + i;
            if (!isPromising(nIdx) || height != line[nIdx] || isLadder[nIdx]) {
                return false;
            }
        }
        return true;
    }

    void installLadder(int idx, boolean[] isLadder) {
        for(int i = 0; i < l; i++) {
            int nIdx = idx + i;

            isLadder[nIdx] = true;
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    boolean isPromising(int idx) {
        return idx >= 0 && idx < n;
    }
}
