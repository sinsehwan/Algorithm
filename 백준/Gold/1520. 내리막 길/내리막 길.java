import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int[][] graph;
    int[][] dp;

    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp[n - 1][m - 1] = 1;

        bw.write(dfs(0, 0) + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = -1;
            }
        }
    }

    int dfs(int y, int x){

        if(dp[y][x] != -1){
            return dp[y][x];
        }

        int count = 0;
        int height = graph[y][x];

        for(int[] v : vec){
            int ny = y + v[0];
            int nx = x + v[1];

            if(isPromising(ny, nx) && graph[ny][nx] < height){
                count += dfs(ny, nx);
            }
        }

        dp[y][x] = count;
        return dp[y][x];
    }

    boolean isPromising(int y, int x){
        return y < n && y >= 0 && x >= 0 && x < m;
    }
}
