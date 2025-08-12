import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m, k, x;
    int[] dist;

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        getInput();

        dist = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE / 2;
        }

        dijkstra(x);

        boolean valid = false;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == k){
                bw.write(i + "\n");
                valid = true;
            }
        }
        if(!valid){
            bw.write(-1 + "\n");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, 1));
        }
    }

    void dijkstra(int st) {
        dist[st] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(st, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.dest] < node.cost) {
                continue;
            }

            for(Node adj : graph.get(node.dest)) {
                if(dist[adj.dest] > dist[node.dest] + adj.cost) {
                    dist[adj.dest] = dist[node.dest] + adj.cost;
                    pq.offer(new Node(adj.dest, dist[adj.dest]));
                }
            }
        }
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
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}