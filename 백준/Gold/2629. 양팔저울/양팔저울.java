import java.io.*;
import java.util.*;


// 접근 어떻게 할까

// Math.max(dp[j +- 추], dp[j])

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int wNum;
    int[] weight;
    int qNum;
    int[] query;
    boolean[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp = new boolean[2 * 40000 + 1];
        //dp[40000] <- 무게 0
        dp[40000] = true;

        for (int i = 0; i < wNum; i++) {
            for (int j = 2 * 40000; j >= weight[i]; j--) {
                dp[j] = dp[j] || dp[j - weight[i]];
            }

            for (int j = 0; j <= 2 * 40000 - weight[i]; j++) {
                dp[j] = dp[j] || dp[j + weight[i]];
            }
        }

        for (int i = 0; i < qNum; i++) {
            if (dp[40000 + query[i]]) {
                bw.write("Y ");
            } else {
                bw.write("N ");
            }
        }
    }

    void getInput() throws IOException {
        wNum = Integer.parseInt(br.readLine());

        weight = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        qNum = Integer.parseInt(br.readLine());

        query = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
