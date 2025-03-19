import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m, r;
    static int[] items;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

    void solve() throws IOException{
        getInput();
    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                graph[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        for(int i = 1; i <= n; i++){
            graph[i][i] = 0;
        }
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b], cost);
            graph[b][a] = Math.min(graph[b][a], cost);
        }

        floyd();

        bw.write(getBest() + "");

        bw.close();
        br.close();
    }

    void floyd(){
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    int getBest(){
        int best = 0;
        for(int i = 1; i <= n; i++){
            int total = 0;
            for(int j = 1; j <= n; j++){
                if(graph[i][j] <= m){
                    total += items[j];
                }
            }
            if(total > best){
                best = total;
            }
        }
        return best;
    }
}
