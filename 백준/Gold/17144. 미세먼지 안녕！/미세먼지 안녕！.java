import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int r, c, t;

    static int[][] graph;

    static int cleanIdx1, cleanIdx2;

    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        getInput();

        for(int i = 0; i < t; i++){
            diffuse();
            clean();
        }

        int total = 0;
        for(int[] li : graph){
            for(int item : li){
                total += item;
            }
        }
        total += 2;
        bw.write(total + "\n");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        graph = new int[r][c];

        for(int i = 0; i < r; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < r; i++){
            if(graph[i][0] == -1){
                cleanIdx1 = i;
                break;
            }
        }
        cleanIdx2 = cleanIdx1 + 1;
    }

    public static void diffuse(){
        int[][] nGraph = new int[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(graph[i][j] > 0){
                    int count = 0;
                    for(int[] v : vec){
                        int ny = i + v[0];
                        int nx = j + v[1];
                        if(isPromising(ny, nx) && graph[ny][nx] != -1){
                            count += 1;
                            nGraph[ny][nx] += graph[i][j] / 5;
                        }
                    }
                    nGraph[i][j] += graph[i][j] - count * (graph[i][j] / 5);
                }
            }
        }
        nGraph[cleanIdx1][0] = -1;
        nGraph[cleanIdx2][0] = -1;
        graph = nGraph;
    }

    public static void clean(){
        //상위
        for(int i = cleanIdx1 - 1; i > 0; i--){
            graph[i][0] = graph[i - 1][0];
        }
        for(int i = 0; i < c - 1; i++){
            graph[0][i] = graph[0][i + 1];
        }
        for(int i = 0; i < cleanIdx1; i++){
            graph[i][c - 1] = graph[i + 1][c - 1];
        }
        for(int i = c - 1; i > 1; i--){
            graph[cleanIdx1][i] = graph[cleanIdx1][i - 1];
        }
        graph[cleanIdx1][1] = 0;

        //하위
        for(int i = cleanIdx2 + 1; i < r - 1; i++){
            graph[i][0] = graph[i + 1][0];
        }
        for(int i = 0; i < c - 1; i++){
            graph[r - 1][i] = graph[r - 1][i + 1];
        }
        for(int i = r - 1; i >= cleanIdx2; i--){
            graph[i][c - 1] = graph[i - 1][c - 1];
        }
        for(int i = c - 1; i > 1; i--){
            graph[cleanIdx2][i] = graph[cleanIdx2][i - 1];
        }
        graph[cleanIdx2][1] = 0;
    }

    public static boolean isPromising(int y, int x){
        return y >= 0 && y < r && x >= 0 && x < c;
    }

    public static void printGraph() throws IOException{
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                bw.write(graph[i][j] + " ");
            }
            bw.write("\n");
        }
    }
}
