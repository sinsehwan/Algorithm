import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    int[][] dist;
    int[][] cost;

    static int count = 1;
    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) {
                break;
            }
            new Main().solve();
            count += 1;
        }
        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        dijkstra();

        bw.write("Problem " + count + ": " + dist[n - 1][n - 1] + "\n");
    }

    void getInput() throws IOException {
        cost = new int[n][n];

        for(int i = 0; i < n; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        dist = new int[n][n];
    }

    void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[0][0] = cost[0][0];
        pq.offer(new Node(0, 0, cost[0][0]));

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if(nd.cost > dist[nd.y][nd.x]) {
                continue;
            }

            for(int[] v : vec){
                int ny = nd.y + v[0];
                int nx = nd.x + v[1];

                if(isPromising(ny, nx)) {
                    if(dist[ny][nx] > dist[nd.y][nd.x] + cost[ny][nx]){
                        dist[ny][nx] = dist[nd.y][nd.x] + cost[ny][nx];
                        pq.offer(new Node(ny, nx, dist[ny][nx]));
                    }
                }
            }


        }
    }

    boolean isPromising(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}

class Node implements Comparable<Node> {
    int y;
    int x;
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
