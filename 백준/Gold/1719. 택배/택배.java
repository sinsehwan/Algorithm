import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    int[][] initPath;
    int[] dist;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j){
                    bw.write("- ");
                }
                else {
                    bw.write(initPath[i][j] + " ");
                }
            }
            bw.write("\n");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        initPath = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    void dijkstra(int st) {
        dist = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE / 2;
        }
        dist[st] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(st, 0));

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if(nd.cost > dist[nd.dest]) continue;

            for(Node next : graph.get(nd.dest)) {
                if(dist[next.dest] > dist[nd.dest] + next.cost) {
                    dist[next.dest] = dist[nd.dest] + next.cost;
                    if(nd.dest == st) {
                        initPath[st][next.dest] = next.dest;
                    }
                    else{
                        initPath[st][next.dest] = initPath[st][nd.dest];
                    }
                    pq.offer(new Node(next.dest, dist[next.dest]));
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
