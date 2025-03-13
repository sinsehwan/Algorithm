import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[][] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();

        calDp();

        int maxTotal = 0;

        for(int i = 0; i < n; i++){
            if(maxTotal < dp[n - 1][i]){
                maxTotal = dp[n - 1][i];
            }
        }

        bw.write(maxTotal + "");

        br.close();
        bw.close();
    }

    static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new int[n][n];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    static void calDp() {
        dp[0][0] = graph[0][0];
        for(int i = 1; i < n; i++){
            for(int j = 0; j <= i; j++){
                dp[i][j] = dp[i - 1][j] + graph[i][j];
                if(j - 1 >= 0){
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + graph[i][j], dp[i][j]);
                }
            }
        }
    }
}
