import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    int s, d;
    int[] dist;

    ArrayList<Set<Edge>> shortest = new ArrayList<>();
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    boolean[][] available;

    public static void main(String[] args) throws IOException {
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
        available = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                available[i][j] = true;
            }
        }

        dijkstra(s);

        int minDist = dist[d];
        if(minDist == Integer.MAX_VALUE / 2) {
            bw.write("-1\n");
            return;
        }

        for(Edge edge : shortest.get(d)){
            available[edge.st][edge.en] = false;
        }

        shortest.clear();
        for(int i = 0; i < n; i++) {
            shortest.add(new HashSet<>());
        }

        dijkstra(s);
        int preMinDist = dist[d];

        if(preMinDist == Integer.MAX_VALUE / 2) {
            bw.write("-1\n");
        }
        else{
            bw.write(preMinDist + "\n");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            shortest.add(new HashSet<>());
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, p));
        }
    }

    void dijkstra(int st) {
        dist = new int[n];
        for(int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE / 2;
        }

        dist[st] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(st, 0));

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if(nd.cost > dist[nd.dest]) continue;

            for(Node next : graph.get(nd.dest)) {
                if(!available[nd.dest][next.dest]) continue;

                if(dist[next.dest] > dist[nd.dest] + next.cost) {
                    dist[next.dest] = dist[nd.dest] + next.cost;
                    shortest.set(next.dest, new HashSet<>(shortest.get(nd.dest)));
                    shortest.get(next.dest).add(new Edge(nd.dest, next.dest));
                    pq.offer(new Node(next.dest, dist[next.dest]));
                }
                else if(dist[next.dest] == dist[nd.dest] + next.cost) {
                    Set<Edge> sh = shortest.get(next.dest);

                    for(Edge edge : shortest.get(nd.dest)) {
                        sh.add(edge);
                    }
                    sh.add(new Edge(nd.dest, next.dest));
                }
            }
        }
    }
}

class Edge {
    int st;
    int en;

    Edge(int st, int en) {
        this.st = st;
        this.en = en;
    }
}

class Node implements Comparable<Node>{
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