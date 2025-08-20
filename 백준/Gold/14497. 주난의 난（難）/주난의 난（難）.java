import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int x1, y1, x2, y2;

    int[][] graph;
    int[][] dist;

    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dist = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        dijkstra(y1, x1);


        bw.write(dist[y2][x2] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;

        graph = new int[n][m];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();

            for(int j = 0; j < m; j++) {
                char c = str.charAt(j);
                // '1' 또는 '#' (범인)은 점프가 필요한 장애물이므로 가중치를 1로 설정합니다.
                if (c == '1' || c == '#') {
                    graph[i][j] = 1;
                }
                // '0' 또는 '*' (시작점)은 비용 없이 이동 가능하므로 가중치를 0으로 설정합니다.
                else { // c == '0' or c == '*'
                    graph[i][j] = 0;
                }
            }
        }
    }

    void dijkstra(int sy, int sx){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(sy, sx, 0));
        dist[sy][sx] = 0;

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if(nd.cost > dist[nd.y][nd.x]) continue;

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

    boolean isPromising(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
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
