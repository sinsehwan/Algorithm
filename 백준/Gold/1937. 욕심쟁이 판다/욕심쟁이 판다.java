import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[][] graph;

    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int best = 0;

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(dp[i][j] == 0) {
                    int result = dfs(i, j);
                    if(result > best){
                        best = result;
                    }
                }
            }
        }
        bw.write(best + "\n");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    int dfs(int y, int x) throws IOException {
        if(dp[y][x] > 0) return dp[y][x];

        int total = 1;
        for(int[] v : vec){
            int ny = y + v[0];
            int nx = x + v[1];

            if(isPromising(ny, nx) && graph[ny][nx] > graph[y][x]){
                total = Math.max(dfs(ny, nx) + 1, total);
            }
        }
        dp[y][x] = total;
        return dp[y][x];
    }

    boolean isPromising(int ny, int nx){
        return ny >= 0 && ny < n && nx >= 0 && nx < n;
    }
}
