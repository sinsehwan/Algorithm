import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, a, b;
    int[][] graph;
    int[][] dp;
    boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    /*
    dp[i + 1][0] = Max(dp[i - 1][0], dp[i - 1][1]) + a
    dp[i + 1][1] = Max(dp[i][0], dp[i][1]) + a
    */
    void solve() throws IOException {
        getInput();
        eratos();

        //세로
        dp[0][1] = calScore(graph[0][0], graph[1][0]);
        if(n > 1) {
            dp[1][1] = dp[0][1] + calScore(graph[0][1], graph[1][1]);

            //가로
            dp[1][0] = calScore(graph[0][0], graph[0][1]) + calScore(graph[1][0], graph[1][1]);
        }
        for(int i = 2; i < n; i++){
            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + calScore(graph[0][i - 1], graph[0][i]) + calScore(graph[1][i - 1], graph[1][i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + calScore(graph[0][i], graph[1][i]);
        }

        bw.write(Math.max(dp[n - 1][0], dp[n - 1][1]) + "");

    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        graph = new int[2][n];
        dp = new int[n][2];

        for(int i = 0; i < 2; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    void eratos(){
        isPrime = new boolean[200001];

        for(int i = 2; i <= 200000; i++){
            isPrime[i] = true;
        }

        for(int i = 2; i * i <= 200000; i++){
            if(isPrime[i]){
                for(int j = i * 2; j <= 200000; j += i){
                    isPrime[j] = false;
                }
            }
        }
    }

    int calScore(int n1, int n2){
        if(isPrime[n1 + n2]){
            return a;
        }
        else{
            return b;
        }
    }
}
