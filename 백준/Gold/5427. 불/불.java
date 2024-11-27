
import java.util.*;
import java.io.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int m, n;
    static char[][] graph;

    static boolean[][] visited;

    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException{

        int caseNum = Integer.parseInt(br.readLine());

        for(int i = 0; i < caseNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            graph = new char[m][n];
            visited = new boolean[m][n];

            for(int j = 0; j < m; j++){
                graph[j] = br.readLine().toCharArray();
            }

            int result = bfs();

            if(result == -1){
                bw.write("IMPOSSIBLE\n");
            }
            else{
                bw.write(result + "\n");
            }
        }


        br.close();
        bw.close();
    }

    static int bfs(){
        LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();
        LinkedList<Tuple<Integer, Integer>> fireQueue = new LinkedList<>();

        int count = 1;

        int sy = 0, sx = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] == '@'){
                    sy = i;
                    sx = j;
                }
            }
        }

        for(int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(graph[i][j] == '*'){
                    fireQueue.offerLast(new Tuple<>(i, j));
                }
            }
        }
        queue.offerLast(new Tuple<>(sy, sx));
        visited[sy][sx] = true;

        while(!queue.isEmpty()){
            //fire 로직
            int qSize = fireQueue.size();

            for(int i = 0; i < qSize; i++){
                Tuple<Integer, Integer> q = fireQueue.pollFirst();

                for(int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && graph[ny][nx] == '.'){
                        graph[ny][nx] = '*';
                        fireQueue.offerLast(new Tuple<>(ny, nx));
                    }
                }
            }
            //이동 로직
            qSize = queue.size();

            for(int i = 0; i < qSize; i++)
            {
                Tuple<Integer, Integer> q = queue.pollFirst();

                if(q.getLeft() == 0 || q.getRight() == 0 || q.getLeft() == m - 1 || q.getRight() == n - 1){
                    return count;
                }

                for(int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && !visited[ny][nx] && graph[ny][nx] == '.'){
                        queue.offerLast(new Tuple<>(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
            count += 1;
        }

        return -1;
    }

    static boolean isPromising(int y, int x){
        return y >= 0 && y < m && x >= 0 && x < n;
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

    public B getRight() {
        return right;
    }
}
