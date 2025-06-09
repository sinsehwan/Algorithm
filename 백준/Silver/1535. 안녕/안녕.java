import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] cost;
    int[] value;
    int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n; i++){
            for(int j = 99; j >= cost[i]; j--){
                dp[j] = Math.max(dp[j - cost[i]] + value[i], dp[j]);
            }
        }

        bw.write(dp[99] + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        cost = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        value = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        dp = new int[100];
    }
}
