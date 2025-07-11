import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int x;
    int[] pipe;
    int[] count;

    int[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp[0] = 1;

        for(int i = 0; i < n; i++){
            for(int j = x; j >= pipe[i]; j--) {
                if(dp[j - pipe[i]] > 0){
                    for(int k = 0; k < count[i]; k++){
                        if(j + k * pipe[i] > x) break;
                        dp[j + k * pipe[i]] += dp[j - pipe[i]];
                    }
                }
            }
        }

        bw.write(dp[x] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        pipe = new int[n];
        count = new int[n];
        dp = new int[x + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            pipe[i] = Integer.parseInt(st.nextToken());
            count[i] = Integer.parseInt(st.nextToken());
        }
    }
}
