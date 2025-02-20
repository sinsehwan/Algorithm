import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static int[][] graph;

    static int maxSize = 1;

    public static void main(String[] args) throws IOException {
        getInput();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int size = findMaxSqare(i, j);
                if(size > maxSize){
                    maxSize = size;
                }
            }
        }

        bw.write(maxSize + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    public static int findMaxSqare(int y, int x){
        int max = 0;
        int iterMax = Math.min(n - 1 - y, m - 1 - x);
        for(int i = 1; i <= iterMax; i++){
            if(isSquare(y, x, i) && (i + 1) * (i + 1) > max){
                max = (i + 1) * (i + 1);
            }
        }
        return max;
    }

    public static boolean isSquare(int y, int x, int lineSize){
        return graph[y][x] == graph[y + lineSize][x] && graph[y][x] == graph[y][x + lineSize] && graph[y][x] == graph[y + lineSize][x + lineSize];
    }
}
