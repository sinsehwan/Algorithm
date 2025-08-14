import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int[][] graph;
    int[][] dist;
    int[][] vec = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dijkstra();

        bw.write(dist[n][m] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            String inp = br.readLine().strip();
            for(int j = 1; j <= m; j++) {
                graph[i][j] = inp.charAt(j - 1) - '0';
            }
        }

        dist = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                dist[i][j] = Integer.MAX_VALUE / 2;
            }
        }
    }

    void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1][1] = 0;
        pq.offer(new Node(1,1, 1));

        while(!pq.isEmpty()) {
            Node nd = pq.poll();


            for(int[] v : vec) {
                int ny = nd.y + v[0];
                int nx = nd.x + v[1];

                if(isPromising(ny, nx)) {
                    if(dist[ny][nx] > dist[nd.y][nd.x] + graph[ny][nx]) {
                        dist[ny][nx] = dist[nd.y][nd.x] + graph[ny][nx];
                        pq.offer(new Node(ny, nx, dist[ny][nx]));
                    }
                }
            }
        }
    }

    boolean isPromising(int y, int x){
        return y > 0 && y <= n && x > 0 && x <= m;
    }
}

class Node implements Comparable<Node>{
    int y, x;
    int cost;

    public Node(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}
