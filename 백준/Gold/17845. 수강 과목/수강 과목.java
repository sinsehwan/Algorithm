import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, k;
    int[] value;
    int[] cost;
    int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < k; i++) {
            for(int j = n; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j - cost[i]] + value[i], dp[j]);
            }
        }

        bw.write(dp[n] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        value = new int[k];
        cost = new int[k];

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            value[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n + 1];
    }
}
