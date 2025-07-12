import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, t;
    int[] cost;
    int[] value;

    int[] dp;

    int total = 0;

    public static void main(String[] args) throws IOException{
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n; i++) {
            for(int j = t; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j - cost[i]] + value[i], dp[j]);
            }
        }

        int best = 0;

        for(int i = 0; i <= t; i++){
            best = Math.max(best, dp[i]);
        }

        bw.write((total - best) + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        cost = new int[n];
        value = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
            total += value[i];
        }

        dp = new int[t + 1];
    }
}
