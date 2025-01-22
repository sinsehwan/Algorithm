import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static int[][] graph;
    static int[][] visited;
    static LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();

    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        getInput();

        int answer = bfs();

        bw.write(answer + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new int[n][m];
        graph = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static int bfs(){
        int count = 0;

        while(isCheeseRemain()){
            visited = new int[n][m];
            queue.offerLast(new Tuple<>(0, 0));
            visited[0][0] = 1;

            while(!queue.isEmpty()){
                int qSize = queue.size();

                for(int i = 0; i < qSize; i++){
                    Tuple<Integer, Integer> node = queue.pollFirst();
                    int py = node.getLeft();
                    int px = node.getRight();

                    for(int[] v : vec){
                        int ny = py + v[0];
                        int nx = px + v[1];

                        if(isPromising(ny, nx)){
                            // 치즈 없는 좌표 방문
                            if(graph[ny][nx] == 0 && visited[ny][nx] == 0){
                                visited[ny][nx] = 1;
                                queue.offerLast(new Tuple<>(ny, nx));
                            }
                            // 치즈 좌표 방문 -> 방문횟수만 체크
                            else if(graph[ny][nx] == 1){
                                visited[ny][nx] += 1;
                            }
                        }
                    }
                }
            }
            eraseCheese();
            //System.out.println(count);
            count += 1;
        }
        return count;
    }

    public static boolean isCheeseRemain(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    public static void eraseCheese(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == 1 && visited[i][j] >= 2){
                    graph[i][j] = 0;
                }
            }
        }
    }

    public static boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}

class Tuple<A,B>{
    private A left;
    private B right;

    public Tuple(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public A getLeft() {
        return left;
    }

    public void setLeft(A left) {
        this.left = left;
    }

    public B getRight() {
        return right;
    }

    public void setRight(B right) {
        this.right = right;
    }
}
