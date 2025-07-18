import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, w;
    int[] weight;
    int[] value;

    int[] dp;

    public static void main(String[] args) throws IOException{
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        weight = new int[n];
        value = new int[n];
        dp = new int[w + 1];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            for(int j = w; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        bw.write(dp[w] + "\n");
    }
}
