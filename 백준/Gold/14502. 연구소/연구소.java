import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[][] initGraph;
    static int[][] graph;
    static LinkedList<Tuple> queue = new LinkedList<>();
    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        getInput();

        bw.write(getBest() + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        initGraph = new int[n][m];

        for(int i = 0; i < n; i++){
            initGraph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        visited = new boolean[n][m];
    }

    public static int getBest() throws IOException{
        int best = 0;

        init();
        for(int i = 0; i < m*n; i++){
            int iy = i / m;
            int ix = i % m;
            if(graph[iy][ix] != 0){
                continue;
            }
            for(int j = i + 1; j < m*n; j++){
                int jy = j / m;
                int jx = j % m;
                if(graph[jy][jx] != 0){
                    continue;
                }
                for(int k = j + 1; k < m*n; k++){
                    int ky = k / m;
                    int kx = k % m;
                    if(graph[ky][kx] != 0){
                        continue;
                    }
                    graph[iy][ix] = 1;
                    graph[jy][jx] = 1;
                    graph[ky][kx] = 1;
                    int result = calRemain();
                    //bw.write("result : " + result + "\n");
                    if(result > best){
                        best = result;
                    }
                    graph[iy][ix] = 0;
                    graph[jy][jx] = 0;
                    graph[ky][kx] = 0;
                    init();
                }
            }
        }

        return best;
    }

    public static int calRemain() throws IOException{
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == 2){
                    queue.offerLast(new Tuple(i, j));
                    //bw.write("detected \n");
                }
            }
        }

        bfs();

        int remain = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == 0){
                    remain += 1;
                }
            }
        }

        return remain;
    }

    public static void init(){
        graph = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                graph[i][j] = initGraph[i][j];
            }
        }
        visited = new boolean[n][m];
    }

    public static void bfs(){

        while(!queue.isEmpty()){
            Tuple t = queue.pollFirst();
            graph[t.getY()][t.getX()] = 2;

            for(int[] v : vec){
                int ny = t.getY() + v[0];
                int nx = t.getX() + v[1];

                if(isPromising(ny, nx) && !visited[ny][nx] && graph[ny][nx] == 0){
                    visited[ny][nx] = true;
                    queue.offerLast(new Tuple(ny, nx));
                }
            }
        }
    }

    public static boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}

class Tuple{
    private int y;
    private int x;

    public Tuple(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
