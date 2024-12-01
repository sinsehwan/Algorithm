
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static int[][] graph;

    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int ilandNum = 2;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] == 1){
                    findIland(i, j);
                    ilandNum += 1;
                }
            }
        }

        /*for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                bw.write(graph[i][j] + " ");
            }
            bw.write("\n");
        }
        */

        int answer = 10000;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int result = bfs(i, j);
                if(result > 0 && result < answer){
                    answer = result;
                }
            }
        }

        bw.write(answer + "");

        br.close();
        bw.close();
    }

    static void findIland(int y, int x){
        LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();

        queue.offerLast(new Tuple<>(y, x));
        graph[y][x] = ilandNum;

        while(!queue.isEmpty()){
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++)
            {
                Tuple<Integer, Integer> q = queue.pollFirst();
                for(int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && graph[ny][nx] == 1){
                        graph[ny][nx] = ilandNum;
                        queue.offerLast(new Tuple<>(ny, nx));
                    }
                }
            }
        }
    }

    static boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    static int bfs(int y, int x){
        boolean[][] visited = new boolean[n][n];

        visited[y][x] = true;

        LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();

        queue.offerLast(new Tuple<>(y, x));
        int count = -1;

        while(!queue.isEmpty()){
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++){
                Tuple<Integer, Integer> q = queue.pollFirst();

                int l = q.getLeft();
                int r = q.getRight();
                if(graph[l][r] != graph[y][x] && graph[l][r] != 0){
                    return count;
                }

                for(int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && graph[ny][nx] != graph[y][x] && !visited[ny][nx]){
                        queue.offerLast(new Tuple<>(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
            count += 1;
        }

        return -1;
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

