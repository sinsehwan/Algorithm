
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static int[][] graph;
    static boolean[][] visited;

    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];

        visited = new boolean[n][n];

        for(int i = 0; i < n; i++)
        {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int answer = 0;
        for(int i = 0; i < 100; i++){

            //초기화 : 모두 false로 두고 i 이하 높이인 지점들 visited로 설정
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if (graph[j][k] <= i){
                        visited[j][k] = true;
                    }
                    else{
                        visited[j][k] = false;
                    }
                }
            }
            int count = 0;
            for(int j = 0; j < n; j++)
            {
                for (int k = 0; k < n; k++)
                {
                    count += bfs(j, k, i);
                }
            }
            if(count > answer){
                answer = count;
            }

        }

        bw.write(answer + "");

        br.close();
        bw.close();
    }

    static int bfs(int sy, int sx, int height){
        if(visited[sy][sx]){
            return 0;
        }

        LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();

        queue.offerLast(new Tuple<>(sy, sx));
        visited[sy][sx] = true;

        while(!queue.isEmpty()){
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++)
            {
                Tuple<Integer, Integer> q = queue.pollFirst();

                for (int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && !visited[ny][nx]){
                        queue.offerLast(new Tuple<>(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
        }


        return 1;
    }

    static boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}


class Tuple<A, B>{
    private A left;
    private B right;

    public Tuple(A left, B right){
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