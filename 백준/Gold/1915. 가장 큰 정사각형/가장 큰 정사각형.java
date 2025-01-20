
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static int[][] graph;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int maxSize = 0;
        for(int i = 0; i < n; i++){
            dp[i][0] = graph[i][0];
            if(graph[i][0] == 1){
                maxSize = 1;
            }
        }
        for(int j = 0; j < m; j++){
            dp[0][j] = graph[0][j];
            if(graph[0][j] == 1){
                maxSize = 1;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(graph[i][j] == 1){
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    if(dp[i][j] > maxSize){
                        maxSize = dp[i][j];
                    }
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }
        /*
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(dp[i][j] + " ");
            }
            bw.write("\n");
        }
         */
        bw.write(maxSize * maxSize + "");

        br.close();
        bw.close();
    }

}
