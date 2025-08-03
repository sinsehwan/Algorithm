import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, k;
    int[] score;
    int[] cost;
    int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n; i++) {
            for(int j = 100; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j - cost[i]] + score[i], dp[j]);
            }

            for(int j = 0; j < k; j++){
                dp[0] = Math.max(dp[j], dp[0]);
            }
            for(int j = k; j <= 100; j++) {
                dp[j - k] = Math.max(dp[j], dp[j - k]);
            }
        }

        int best = 0;
        for(int i = 0; i <= 100; i++){
            best = Math.max(best, dp[i]);
        }
        bw.write(best + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        score = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        cost = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        dp = new int[101];
    }
}
