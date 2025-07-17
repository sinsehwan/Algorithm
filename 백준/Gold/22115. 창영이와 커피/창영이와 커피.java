import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, k;
    int[] cost;
    // dp[i] : i만큼의 카페인을 흡수하기 위한 최소 커피 개수
    int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp[0] = 0;

        for(int i = 0; i < n; i++) {
            for(int j = k; j >= cost[i]; j--) {
                dp[j] = Math.min(dp[j - cost[i]] + 1, dp[j]);
            }
        }

        if(dp[k] == Integer.MAX_VALUE / 2) {
            bw.write(-1 + "");
        }
        else{
            bw.write(dp[k] + "");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cost = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        dp = new int[k + 1];
        for(int i = 0; i <= k; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
        }
    }
}
