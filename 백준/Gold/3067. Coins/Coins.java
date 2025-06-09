import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] coins;
    int target;
    int[] dp;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n; i++){
            for(int j = coins[i]; j <= target; j++){
                dp[j] += dp[j - coins[i]];
            }
        }

        bw.write(dp[target] + "\n");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        coins = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        target = Integer.parseInt(br.readLine());
        dp = new int[target + 1];
        dp[0] = 1;
    }
}
