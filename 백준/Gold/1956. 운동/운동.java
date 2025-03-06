import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int v, e;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        getInput();

        floyd();

        int best = getBest();

        if(best > Integer.MAX_VALUE / 2){
            bw.write("-1");
        }
        else{
            bw.write(best + "");
        }
        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new int[v][v];

        for(int i = 0; i < v; i++){
            for(int j = 0; j < v; j++){
                graph[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for(int i = 0; i < v; i++){
            graph[i][i] = 0;
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b], cost);
        }
    }

    public static void floyd() {
        for(int k = 0; k < v; k++){
            for(int i = 0; i < v; i++){
                for(int j = 0; j < v; j++){
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                }
            }
        }
    }

    public static int getBest(){
        int best = Integer.MAX_VALUE;

        for(int i = 0; i < v; i++){
            for(int j = 0; j < v; j++){
                if(i == j){
                    continue;
                }
                int cycleCost = graph[i][j] + graph[j][i];
                if(cycleCost < best){
                    best = cycleCost;
                }
            }
        }
        return best;
    }
}
