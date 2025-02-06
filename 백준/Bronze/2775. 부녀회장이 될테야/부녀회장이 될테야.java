import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] graph;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            graph = new int[k + 1][n + 1];

            bw.write(findMember(k, n) + "\n");
        }
        br.close();
        bw.close();
    }

    public static int findMember(int y, int x){
        if(y == 0){
            return x;
        }
        if(x == 1){
            return 1;
        }
        int v1, v2;
        if(graph[y - 1][x] != 0){
            v1 = graph[y - 1][x];
        }
        else{
            v1 = findMember(y - 1, x);
        }

        if(graph[y][x - 1] != 0){
            v2 = graph[y][x - 1];
        }
        else{
            v2 = findMember(y, x - 1);
        }

        graph[y][x] = v1 + v2;
        return graph[y][x];
    }
}
