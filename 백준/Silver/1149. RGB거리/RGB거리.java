import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

    void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][3];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][3];

        for(int i = 0; i < 3; i++){
            dp[0][i] = cost[0][i];
        }
        for(int i = 1; i < n; i++){
            dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i - 1][1], dp[i - 1][0]);
        }


        int answer = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
        bw.write(answer + "");
        br.close();
        bw.close();
    }


}
