import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][m];
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        dp[0][0] = graph[0][0];
        for(int i = 1; i < n; i++){
            dp[i][0] = graph[i][0] + dp[i - 1][0];
        }
        for(int i = 1; i < m; i++){
            dp[0][i] = graph[0][i] + dp[0][i - 1];
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + graph[i][j];
            }
        }

        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;

            int answer = dp[y2][x2];
            if(x1 > 0){
                answer -= dp[y2][x1 - 1];
            }
            if(y1 > 0){
                answer -= dp[y1 - 1][x2];
            }
            if(x1 > 0 && y1 > 0){
                answer += dp[y1 - 1][x1 - 1];
            }

            bw.write(answer + "\n");
        }
    }
}
