import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static int[][] graph;

    public static void main(String[] args) throws IOException {
        getInput();

        /*for(int[] li : graph){
            for(int item : li){
                if(item >= 0){
                    bw.write(item + " ");
                }
                else{
                    bw.write("0 ");
                }
            }
            bw.write("\n");
        }

         */

        floyd();

        for(int[] li : graph){
            for(int item : li){
                if(item < Integer.MAX_VALUE / 2){
                    bw.write(item + " ");
                }
                else{
                    bw.write("0 ");
                }
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                graph[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b], cost);
        }

        for(int i = 0; i < n; i++){
            graph[i][i] = 0;
        }
    }

    public static void floyd(){
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
