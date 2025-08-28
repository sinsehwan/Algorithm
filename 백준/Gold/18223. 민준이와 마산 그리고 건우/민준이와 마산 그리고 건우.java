import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int v, e, p;
    int[] dist;
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dijkstra(1);
        int cost = dist[v];
        int pCost = dist[p];
        dijkstra(p);
        int pcost = dist[v];

        if(cost == pCost + pcost) {
            bw.write("SAVE HIM");
        }
        else{
            bw.write("GOOD BYE");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        dist = new int[v + 1];

        for(int i = 0; i <= v; i++) {
            dist[i] = Integer.MAX_VALUE / 2;
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    void dijkstra(int st) {
        for(int i = 0; i <= v; i++) {
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