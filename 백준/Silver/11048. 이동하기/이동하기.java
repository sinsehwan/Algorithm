import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int n, m;

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n][m];

        for(int i = 0; i < n; i++){
            dp[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 1; i < n; i++){
            dp[i][0] += dp[i - 1][0];
        }

        for(int j = 1; j < m; j++){
            dp[0][j] += dp[0][j - 1];
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                dp[i][j] += Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        bw.write(dp[n - 1][m - 1] + "");

        br.close();
        bw.close();
    }
}
