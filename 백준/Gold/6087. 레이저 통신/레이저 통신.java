import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    char[][] graph;
    int[][][] dist;
    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int n, m;
    int sy = -1, sx = -1, ey = -1, ex = -1;
    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dijkstra(sy, sx);

        int best = Integer.MAX_VALUE / 2;
        for(int i = 0; i < 4; i++) {
            best = Math.min(best, dist[ey][ex][i]);
        }

        bw.write(best + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new char[n][m];

        for(int i = 0; i < n; i++) {
            String line = br.readLine();

            for(int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j);
                if(graph[i][j] == 'C') {
                    if(sy == -1) {
                        sy = i;
                        sx = j;
                    }
                    else{
                        ey = i;
                        ex = j;
                    }
                }
            }
        }
    }

    void dijkstra(int sy, int sx) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[n][m][4];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < 4; k++) {
                    dist[i][j][k] = Integer.MAX_VALUE / 2;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            int ny = sy + vec[i][0];
            int nx = sx + vec[i][1];
            if(isPromising(ny, nx) && graph[ny][nx] != '*') {
                dist[ny][nx][i] = 0;
                pq.offer(new Node(ny, nx, i, 0));
            }
        }

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if(nd.cost > dist[nd.y][nd.x][nd.vIdx]) continue;

            for(int i = 0; i < 4; i++) {
                int ny = nd.y + vec[i][0];
                int nx = nd.x + vec[i][1];

                int nCost;
                if(nd.vIdx == i) {
                    nCost = 0;
                }
                else{
                    nCost = 1;
                }

                if(isPromising(ny, nx) && graph[ny][nx] != '*' && dist[ny][nx][i] > dist[nd.y][nd.x][nd.vIdx] + nCost) {
                    dist[ny][nx][i] = dist[nd.y][nd.x][nd.vIdx] + nCost;
                    pq.offer(new Node(ny, nx, i, dist[ny][nx][i]));
                }
            }
        }
    }

    boolean isPromising(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}

class Node implements Comparable<Node>{
    int y, x;
    int vIdx;
    int cost;

    public Node(int y, int x, int vIdx, int cost) {
        this.y = y;
        this.x = x;
        this.vIdx = vIdx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}
