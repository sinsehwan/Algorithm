
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] wines;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        wines = new int[n];
        dp = new int[n][2];

        for(int i = 0; i < n; i++){
            wines[i] = Integer.parseInt(br.readLine());
        }

        dp[0][0] = wines[0];
        dp[0][1] = 1;

        if(n > 1){
            dp[1][0] = dp[0][0] + wines[1];
            dp[1][1] = 2;
        }

        if(n > 2){
            dp[2][0] = dp[1][0];
            dp[2][1] = 0;

            if(dp[2][0] < wines[0] + wines[2]){
                dp[2][0] = wines[0] + wines[2];
                dp[2][1] = 1;
            }
            if(dp[2][0] < wines[1] + wines[2]){
                dp[2][0] = wines[1] + wines[2];
                dp[2][1] = 2;
            }
        }

        for(int i = 3; i < n; i++){
            if(dp[i - 1][1] == 2){
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = 0;
            }
            else{
                dp[i][0] = dp[i - 1][0] + wines[i];
                dp[i][1] = dp[i - 1][1] + 1;
            }

            if(dp[i][0] < dp[i - 2][0] + wines[i]){
                dp[i][0] = dp[i - 2][0] + wines[i];
                dp[i][1] = 1;
            }
            if(dp[i][0] < dp[i - 3][0] + wines[i - 1] + wines[i]){
                dp[i][0] = dp[i - 3][0] + wines[i - 1] + wines[i];
                dp[i][1] = 2;
            }
        }

        bw.write(dp[n - 1][0] + "");

        br.close();
        bw.close();
    }
}
