import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int s, t;
    int[] dist;
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
        dist = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE / 2;
        }

        dijkstra(s);
        bw.write(dist[t] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
    }

    void dijkstra(int st) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(st, 0));
        dist[st] = 0;

        while(!pq.isEmpty()) {
            Node nd = pq.poll();
            if(dist[nd.dest] < nd.cost) continue;

            for(Node next : graph.get(nd.dest)) {
                if(dist[next.dest] > dist[nd.dest] + next.cost) {
                    dist[next.dest] = dist[nd.dest] + next.cost;
                    pq.offer(next);
                }
            }
        }

    }
}

class Node implements Comparable<Node> {
    int dest;
    int cost;

    Node(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}
