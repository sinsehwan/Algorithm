
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static int[][] graph;
    static boolean[][] visited;

    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int count = 0;

        while(true) {
            int icebergNum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    visited[i][j] = false;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && graph[i][j] > 0) {
                        checkIceberg(i, j);
                        icebergNum += 1;
                    }
                }
            }

            if (icebergNum >= 2) {
                bw.write(count + "");
                break;
            } else if (icebergNum == 0) {
                bw.write(0 + "");
                break;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    visited[i][j] = false;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && graph[i][j] == 0) {
                        meltIceberg(i, j);
                    }
                }
            }

            count += 1;
        }

        br.close();
        bw.close();
    }

    static void meltIceberg(int y, int x){
        LinkedList<Tuple<Integer, Integer>> meltingIce = new LinkedList<>();

        meltingIce.offerLast(new Tuple<>(y, x));
        visited[y][x] = true;

        while(!meltingIce.isEmpty())
        {
            int qSize = meltingIce.size();
            for(int i = 0; i < qSize; i++){
                Tuple<Integer, Integer> q = meltingIce.pollFirst();

                for(int v[] : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && !visited[ny][nx]){
                        if(graph[ny][nx] > 0){
                            graph[ny][nx] -= 1;
                            if (graph[ny][nx] == 0){
                                visited[ny][nx] = true;
                            }
                        }
                        else{
                            visited[ny][nx] = true;
                            meltingIce.offerLast(new Tuple<>(ny, nx));
                        }
                    }
                }
            }
        }
    }

    static void checkIceberg(int y, int x){
        LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();

        queue.offerLast(new Tuple<>(y, x));
        visited[y][x] = true;

        while(!queue.isEmpty()){
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++){
                Tuple<Integer, Integer> q = queue.pollFirst();

                for(int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];
                    if(isPromising(ny, nx) && !visited[ny][nx] && graph[ny][nx] > 0){
                        visited[ny][nx] = true;
                        queue.offerLast(new Tuple<>(ny, nx));
                    }
                }
            }
        }
    }

    static boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}

class Tuple<A, B>{
    private A left;
    private B right;

    Tuple(A left, B right){
        this.left = left;
        this.right = right;
    }

    public A getLeft(){
        return left;
    }

    public B getRight(){
        return right;
    }
}
