import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int t, c;
    int start, end;
    int[] dist;

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
        dist = new int[t + 1];
        for(int i = 0; i <= t; i++) {
            dist[i] = Integer.MAX_VALUE / 2;
        }

        dijkstra(start);

        bw.write(dist[end] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= t; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    void dijkstra(int st) {
        dist[st] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(st, 0));

        while(!pq.isEmpty()) {
            Node nd = pq.poll();
            if(nd.cost > dist[nd.dest]) continue;
            for(Node next : graph.get(nd.dest)) {
                if(dist[next.dest] > dist[nd.dest] + next.cost){
                    dist[next.dest] = dist[nd.dest] + next.cost;
                    pq.offer(new Node(next.dest, dist[next.dest]));
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
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}