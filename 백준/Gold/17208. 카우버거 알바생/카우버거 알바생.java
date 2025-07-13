import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m, k;
    int[][] order;

    int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n; i++) {
            for(int j = m; j >= order[i][0]; j--){
                for(int o = k; o >= order[i][1]; o--) {
                    dp[j][o] = Math.max(dp[j - order[i][0]][o - order[i][1]] + 1, dp[j][o]);
                }
            }
        }

        int best = 0;
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= k; j++) {
                best = Math.max(best, dp[i][j]);
            }
        }

        bw.write(best + "\n");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        order = new int[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            order[i][0] = Integer.parseInt(st.nextToken());
            order[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[m + 1][k + 1];
    }
}
