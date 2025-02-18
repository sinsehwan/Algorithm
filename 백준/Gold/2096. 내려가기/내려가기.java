import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[][] graph;

    static int[][] minDp;
    static int[][] maxDp;

    public static void main(String[] args) throws IOException {
        getInput();

        for(int i = 0; i < 3; i++){
            minDp[0][i] = graph[0][i];
            maxDp[0][i] = graph[0][i];
        }

        calMinDp();
        calMaxDp();

        int best = Math.max(maxDp[n - 1][0], Math.max(maxDp[n - 1][1], maxDp[n - 1][2]));
        int worst =  minDp[n - 1][1] = Math.min(minDp[n - 1][0], Math.min(minDp[n - 1][1], minDp[n - 1][2]));


        bw.write(best + " " + worst);
        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        graph = new int[n][3];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                int item = Integer.parseInt(st.nextToken());
                graph[i][j] = item;
            }
        }
        minDp = new int[n][3];
        maxDp = new int[n][3];
    }

    public static void calMinDp(){
        for(int i = 1; i < n; i++){
            minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + graph[i][0];
            minDp[i][1] = Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2])) + graph[i][1];
            minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + graph[i][2];
        }
    }

    public static void calMaxDp(){
        for(int i = 1; i < n; i++){
            maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + graph[i][0];
            maxDp[i][1] = Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2])) + graph[i][1];
            maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + graph[i][2];
        }
    }
}
