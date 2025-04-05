import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dice;
    int[][] vec = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] graph;
    int n, m;
    int[][] score;
    boolean[][] visited;

    int turn;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        init();

        bw.write(doGame() + "");
    }

    void init() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        turn = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        score = new int[n][m];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        visited = new boolean[n][m];

        // 칸당 점수 미리 계산
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j]){
                    bfs(i, j);
                }
            }
        }

        //dice
        dice = new int[6];
        dice[0] = 6;
        dice[1] = 5;
        dice[2] = 1;
        dice[3] = 2;
        dice[4] = 4;
        dice[5] = 3;
    }

    void bfs(int y, int x) {
        LinkedList<Node> queue = new LinkedList<>();
        ArrayList<Node> sameList = new ArrayList<>();

        queue.offerLast(new Node(y, x));
        sameList.add(new Node(y, x));
        visited[y][x] = true;

        int sum = 0;
        while(!queue.isEmpty()){

            int qSize = queue.size();

            for(int i = 0; i < qSize; i++){
                Node node = queue.pollFirst();
                sum += graph[node.y][node.x];
                sameList.add(node);

                for(int[] v : vec){
                    int ny = v[0] + node.y;
                    int nx = v[1] + node.x;

                    if(isPromising(ny, nx) && !visited[ny][nx] && graph[node.y][node.x] == graph[ny][nx]){
                        queue.offerLast(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        for (Node n : sameList){
            score[n.y][n.x] = sum;
        }
    }

    boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    int doGame() {
        int totalScore = 0;

        int dy = 0;
        int dx = 0;
        int dir = 0;

        for(int i = 0; i < turn; i++){
            int ty = dy + vec[dir][0];
            int tx = dx + vec[dir][1];

            if(!isPromising(ty, tx)){
                dir = (dir + 2) % 4;
                ty = dy + vec[dir][0];
                tx = dx + vec[dir][1];
            }
            dy = ty;
            dx = tx;

            roll(dir);
            if(dice[0] > graph[dy][dx]){
                dir = (dir + 1) % 4;
            }
            else if(dice[0] < graph[dy][dx]){
                dir = (dir + 3) % 4;
            }
            totalScore += score[dy][dx];
        }

        return totalScore;
    }

    void roll(int dir){
        // 우 하 좌 상
        if(dir == 0){
            //우
            int temp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[2];
            dice[2] = dice[4];
            dice[4] = temp;
        }
        else if(dir == 1){
            // 하
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = temp;
        }
        else if(dir == 2){
            //좌
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[5];
            dice[5] = temp;
        }
        else{
            //상
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] = temp;
        }
    }
}

class Node {
    int y;
    int x;
    public Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}
