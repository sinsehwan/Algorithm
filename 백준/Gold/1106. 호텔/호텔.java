import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c, n;

    int[] cost;
    int[] value;
    int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        getInput();

        dp[0] = 0;

        for(int i = 0; i < n; i++) {
            for(int j = value[i]; j <= c + 100; j++) {
                dp[j] = Math.min(dp[j - value[i]] + cost[i], dp[j]);
            }
        }

        int best = Integer.MAX_VALUE / 2;

        for(int i = c; i <= c + 100; i++) {
            best = Math.min(best, dp[i]);
        }
        bw.write(best + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        cost = new int[n];
        value = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[c + 101];

        for(int i = 0; i <= c + 100; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
        }
    }
}
