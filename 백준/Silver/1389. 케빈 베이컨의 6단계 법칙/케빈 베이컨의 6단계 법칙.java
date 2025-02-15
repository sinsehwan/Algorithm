import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static int[][] graph;

    public static void main(String[] args) throws IOException {
        getInput();

        dijkstra();

        int[] bacon = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                bacon[i] += graph[i][j];
            }
        }

        int best = 10000;
        int idx = -1;
        for(int i = 0; i < n; i++){
            if(bacon[i] < best){
                idx = i;
                best = bacon[i];
            }
        }
        bw.write(idx + 1 + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                graph[i][j] = 10000;
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a - 1][b - 1] = 1;
            graph[b - 1][a - 1] = 1;
        }

        for(int i = 0; i < n; i++){
            graph[i][i] = 0;
        }
    }

    public static void dijkstra() {

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(graph[i][k] + graph[k][j] < graph[i][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }
}
