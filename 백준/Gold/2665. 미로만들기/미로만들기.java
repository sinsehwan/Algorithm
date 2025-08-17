import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[][] dist;
    boolean[][] graph;
    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
        dist = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                dist[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        dijkstra(1, 1);

        bw.write(dist[n][n] + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        graph = new boolean[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            String line = br.readLine();

            for(int j = 1; j <= n; j++) {
                graph[i][j] = line.charAt(j - 1) == '0';
            }
        }
    }

    void dijkstra(int sy, int sx) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[sy][sx] = 0;
        pq.offer(new Node(sy, sx, 0));

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if(dist[nd.y][nd.x] < nd.cost) {
                continue;
            }

            for (int[] v : vec) {
                int ny = nd.y + v[0];
                int nx = nd.x + v[1];

                if(isPromising(ny, nx)) {
                    int moveCost = graph[ny][nx] ? 1 : 0;
                    if(dist[ny][nx] > dist[nd.y][nd.x] + moveCost) {
                        dist[ny][nx] = dist[nd.y][nd.x] + moveCost;
                        pq.offer(new Node(ny, nx, dist[ny][nx]));
                    }
                }
            }
        }
    }

    boolean isPromising(int y, int x) {
        return y > 0 && y <= n && x > 0 && x <= n;
    }
}

class Node implements Comparable<Node> {
    int y;
    int x;
    int cost;

    Node(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}
