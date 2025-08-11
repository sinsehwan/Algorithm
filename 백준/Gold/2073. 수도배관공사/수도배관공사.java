import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int d, p;

    int[] length;
    int[] capacity;
    int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp[0] = Integer.MAX_VALUE / 2;
        for(int i = 0; i < p; i++) {
            for(int j = d; j >= length[i]; j--) {
                dp[j] = Math.max(Math.min(dp[j - length[i]], capacity[i]), dp[j]);
            }
        }

        bw.write(dp[d] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        length = new int[p];
        capacity = new int[p];

        for(int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            length[i] = Integer.parseInt(st.nextToken());
            capacity[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[d + 1];
    }
}
