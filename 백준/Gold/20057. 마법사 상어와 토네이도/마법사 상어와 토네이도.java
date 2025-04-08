import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int answer = 0;

    int n;
    int[][] vec = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    int[][] graph;
    int loss = 0;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int ty = n / 2;
        int tx = n / 2;

        int dir = 1;
        int iterNum = 0;

        for(int i = 0; i < (n - 1) * 2 ; i++){
            if(i % 2 == 0){
                iterNum += 1;
            }
            for(int j = 0; j < iterNum; j++){
                tornado(ty, tx, dir);
                ty += vec[dir][0];
                tx += vec[dir][1];
            }
            dir = (dir + 1) % 4;
        }

        for(int i = 0; i < iterNum; i++){
            tornado(ty, tx, dir);
            ty += vec[dir][0];
            tx += vec[dir][1];
        }

        bw.write(answer + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    void tornado(int y, int x, int dir) {
        int ny = y + vec[dir][0];
        int nx = x + vec[dir][1];

        int sand = graph[ny][nx];
        graph[ny][nx] = 0;
        loss = 0;

        if(dir == 0){
            //상
            int partial = sand * 7 / 100;
            addOrthrow(ny, nx - 1, partial);
            addOrthrow(ny, nx + 1, partial);

            partial = sand / 100;
            addOrthrow(ny + 1, nx - 1, partial);
            addOrthrow(ny + 1, nx + 1, partial);

            partial = sand * 10 / 100;
            addOrthrow(ny - 1, nx - 1, partial);
            addOrthrow(ny - 1, nx + 1, partial);

            //2칸
            partial = sand * 2 / 100;
            addOrthrow(ny, nx - 2, partial);
            addOrthrow(ny, nx + 2, partial);
            partial = sand * 5 / 100;
            addOrthrow(ny - 2, nx, partial);

        }
        else if(dir == 1){
            //좌
            int partial = sand * 7 / 100;
            addOrthrow(ny - 1, nx, partial);
            addOrthrow(ny + 1, nx, partial);

            partial = sand / 100;
            addOrthrow(ny - 1, nx + 1, partial);
            addOrthrow(ny + 1, nx + 1, partial);

            partial = sand * 10 / 100;
            addOrthrow(ny - 1, nx - 1, partial);
            addOrthrow(ny + 1, nx - 1, partial);

            //2칸
            partial = sand * 2 / 100;
            addOrthrow(ny - 2, nx, partial);
            addOrthrow(ny + 2, nx, partial);
            partial = sand * 5 / 100;
            addOrthrow(ny, nx - 2, partial);
        }
        else if(dir == 2){
            //하
            int partial = sand * 7 / 100;
            addOrthrow(ny, nx - 1, partial);
            addOrthrow(ny, nx + 1, partial);

            partial = sand / 100;
            addOrthrow(ny - 1, nx - 1, partial);
            addOrthrow(ny - 1, nx + 1, partial);

            partial = sand * 10 / 100;
            addOrthrow(ny + 1, nx - 1, partial);
            addOrthrow(ny + 1, nx + 1, partial);

            //2칸
            partial = sand * 2 / 100;
            addOrthrow(ny, nx - 2, partial);
            addOrthrow(ny, nx + 2, partial);
            partial = sand * 5 / 100;
            addOrthrow(ny + 2, nx, partial);
        }
        else if(dir == 3){
            //우
            int partial = sand * 7 / 100;
            addOrthrow(ny - 1, nx, partial);
            addOrthrow(ny + 1, nx, partial);

            partial = sand / 100;
            addOrthrow(ny - 1, nx - 1, partial);
            addOrthrow(ny + 1, nx - 1, partial);

            partial = sand * 10 / 100;
            addOrthrow(ny - 1, nx + 1, partial);
            addOrthrow(ny + 1, nx + 1, partial);

            //2칸
            partial = sand * 2 / 100;
            addOrthrow(ny - 2, nx, partial);
            addOrthrow(ny + 2, nx, partial);
            partial = sand * 5 / 100;
            addOrthrow(ny, nx + 2, partial);
        }

        addOrthrow(ny + vec[dir][0], nx + vec[dir][1], sand - loss);
        //System.out.println((ny + vec[dir][0]) + " " + (nx + vec[dir][1]) + " " + (sand - loss) + " " + sand + " " + loss);
    }

    void addOrthrow(int y, int x, int s){
        if(y >= 0 && y < n && x >= 0 && x < n){
            graph[y][x] += s;
        }
        else{
            answer += s;
        }
        loss += s;
    }
}
