import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m, dy, dx, k;
    int[][] graph;
    int[] dice = {0, 0, 0, 0, 0, 0};
    // 동 서 북 남
    int[][] vec = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    int[] command;


    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        getInput();

        for(int i = 0; i < k; i++){
            doGame(command[i]);
        }
    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());
        dx = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        command = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }

    void doGame(int dir) throws IOException {
        int ny = dy + vec[dir][0];
        int nx = dx + vec[dir][1];

        if(!isPromising(ny, nx)){
            return;
        }
        roll(dir);
        dy = ny;
        dx = nx;

        if(graph[dy][dx] == 0){
            graph[dy][dx] = dice[0];
        }
        else{
            dice[0] = graph[dy][dx];
            graph[dy][dx] = 0;
        }
        bw.write(dice[2] + "\n");
    }

    boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    void roll(int dir){
        if(dir == 1){
            // 동
            int temp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[2];
            dice[2] = dice[4];
            dice[4] = temp;
        }
        else if(dir == 2){
            // 서
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[5];
            dice[5] = temp;
        }
        else if(dir == 3){
            // 북
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] = temp;
        }
        else{
            // 남
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = temp;
        }
    }
}
