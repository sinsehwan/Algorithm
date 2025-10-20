import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int r, c, n;

    Bomb[][] graph;

    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for (int i = 2; i <= n; i++) {
            doTurn(i);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (graph[i][j].isValid) {
                    bw.write("O");
                }
                else {
                    bw.write(".");
                }
            }
            bw.write("\n");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new Bomb[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();

            for (int j = 0; j < c; j++) {
                if (line.charAt(j) == '.') {
                    graph[i][j] = new Bomb(-1, false);
                }
                else{
                    graph[i][j] = new Bomb(3, true);
                }
            }
        }
    }

    void doTurn(int time) {
        if (time % 2 == 0) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (!graph[i][j].isValid) {
                        graph[i][j] = new Bomb(time + 3, true);
                    }
                }
            }
        }
        else {
            LinkedList<Node> pung = new LinkedList<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (graph[i][j].isValid && graph[i][j].bombTime == time) {
                        pung.add(new Node(i, j));
                    }
                }
            }
            for (Node nd : pung) {
                graph[nd.y][nd.x] = new Bomb(-1, false);

                for (int[] v : vec) {
                    int ny = nd.y + v[0];
                    int nx = nd.x + v[1];

                    if(isPromising(ny, nx)) {
                        graph[ny][nx] = new Bomb(-1, false);
                    }
                }
            }
        }
    }

    boolean isPromising(int y, int x) {
        return y >= 0 && y < r && x >= 0 && x < c;
    }
}

class Bomb {
    int bombTime;
    boolean isValid;

    public Bomb(int bombTime, boolean isValid) {
        this.bombTime = bombTime;
        this.isValid = isValid;
    }
}

class Node {
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
