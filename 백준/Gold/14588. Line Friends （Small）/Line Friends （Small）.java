import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, q;
    static int[][] lines;
    static int[][] graph;
    static int[][] questions;


    public static void main(String[] args) throws IOException {
        getInput();

        init();

        floyd();

        for(int[] q : questions){
            if(graph[q[0]][q[1]] != Integer.MAX_VALUE / 2){
                bw.write(graph[q[0]][q[1]] + "\n");
            }
            else{
                bw.write("-1\n");
            }
        }

        br.close();
        bw.close();
    }

    static void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        lines = new int[n][2];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        q = Integer.parseInt(br.readLine());
        questions = new int[q][2];
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            questions[i][0] = Integer.parseInt(st.nextToken()) - 1;
            questions[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
    }

    static void init(){
        graph = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                graph[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        for(int i = 0; i < n; i++){
            graph[i][i] = 0;
        }

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isFold(lines[i][0], lines[i][1], lines[j][0], lines[j][1])){
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }
    }

    static boolean isFold(int s1, int e1, int s2, int e2) {
        return s1 <= s2 && s2 <= e1 || s2 < s1 && s1 <= e2;
    }

    static void floyd(){
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }
}
