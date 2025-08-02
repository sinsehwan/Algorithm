import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    // block[i][j] : i의 j번째 블럭
    int[][] blocks;
    int h;
    int[] dp;
    int[][] temp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp[0] = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < blocks[i].length; j++) {
                for (int k = h; k >= blocks[i][j]; k--) {
                    temp[k][j] = dp[k - blocks[i][j]];
                }
            }
            for(int j = 0; j < blocks[i].length; j++) {
                for(int k = h; k >= blocks[i][j]; k--) {
                    dp[k] = (dp[k] + temp[k][j]) % 10007;
                }
            }
        }

        bw.write(dp[h] + "\n");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        blocks = new int[n][m];
        for(int i = 0; i < n; i++) {
            blocks[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Arrays.sort(blocks[i]);
        }

        dp = new int[h + 1];
        temp = new int[h + 1][m];
    }
}
