import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int k;
    int[] cost;
    int[][] dp;
    int[] move = {1, 3, 2};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp[0][0] = 0;

        int prePosition;

        for(int i = 0; i < 3; i++) {
            for(int j = cost[i]; j <= k; j++) {
                for(int k = 0; k < 4; k++) {
                    prePosition = (k - move[i] + 4) % 4;
                    dp[j][k] = Math.min(dp[j - cost[i]][prePosition] + 1, dp[j][k]);
                }
            }
        }

        if(dp[k][0] == Integer.MAX_VALUE / 2) {
            bw.write("-1");
        }
        else{
            bw.write(dp[k][0] + "");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        cost = new int[3];
        for(int i = 0; i < 3; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        k = Integer.parseInt(st.nextToken());

        dp = new int[k + 1][4];
        for(int i = 0; i <= k; i++){
            for(int j = 0; j < 4; j++) {
                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }
    }
}
