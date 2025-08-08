import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, k;
    int[] cost1;
    int[] value1;
    int[] cost2;
    int[] value2;

    int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= k; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                if(j >= cost1[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost1[i]] + value1[i]);
                }
                if(j >= cost2[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost2[i]] + value2[i]);
                }
            }

        }


        bw.write(dp[n][k] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cost1 = new int[n + 1];
        cost2 = new int[n + 1];
        value1 = new int[n + 1];
        value2 = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            cost1[i] = Integer.parseInt(st.nextToken());
            value1[i] = Integer.parseInt(st.nextToken());
            cost2[i] = Integer.parseInt(st.nextToken());
            value2[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n + 1][k + 1];
    }
}
