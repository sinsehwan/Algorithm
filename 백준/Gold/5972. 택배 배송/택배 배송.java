import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    int n, m;
    int[] dist;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dijkstra(1);

        bw.write(dist[n] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE / 2;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if(dist[nd.dest] < nd.cost) {
                continue;
            }

            for(Node adj : graph.get(nd.dest)) {
                if(dist[adj.dest] > dist[nd.dest] + adj.cost) {
                    dist[adj.dest] = dist[nd.dest] + adj.cost;
                    pq.offer(new Node(adj.dest, dist[adj.dest]));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int dest;
    int cost;

    public Node(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}
