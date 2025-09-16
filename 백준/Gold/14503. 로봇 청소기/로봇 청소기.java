import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int r, c, d;
    int[][] graph;
    int[][] vec = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    int answer = 0;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        while(true) {
            if(graph[r][c] == 0) {
                cleanUp(r, c);
            }

            if(isRemain(r, c)) {
                boolean moved = false;
                for(int i = 1; i <= 4; i++) {
                    int nd = (d - i + 4) % 4;
                    int nr = r + vec[nd][0];
                    int nc = c + vec[nd][1];

                    if(isPromising(nr, nc) && graph[nr][nc] == 0) {
                        r = nr;
                        c = nc;
                        d = nd;
                        moved = true;
                        break;
                    }
                }
                if(!moved) break;
            }
            else{
                r -= vec[d][0];
                c -= vec[d][1];
                if(!isPromising(r, c)) break;
            }
        }

        bw.write(answer + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    void cleanUp(int y, int x) {
        graph[y][x] = 2;
        answer += 1;
    }

    boolean isRemain(int y, int x) {
        for(int[] v : vec) {
            int ny = y + v[0];
            int nx = x + v[1];

            if(isPromising(ny, nx) && graph[ny][nx] == 0) {
                return true;
            }
        }
        return false;
    }
    boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m && graph[y][x] != 1;
    }
}
