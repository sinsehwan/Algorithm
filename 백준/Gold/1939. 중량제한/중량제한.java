import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int[] throughput;
    int start, end;
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dijkstra(start);

        bw.write(throughput[end] + "\n");
/*
        for(int i = 1; i <= n; i++) {
            bw.write(throughput[i] + "\n");

        }
 */
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        throughput = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a, b, c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, Integer.MAX_VALUE / 2));
        throughput[start] = Integer.MAX_VALUE / 2;

        while(!pq.isEmpty()) {
            Node nd = pq.poll();

            if (nd.cost < throughput[nd.dest]) {
                continue;
            }

            for (Node next : graph.get(nd.dest)) {
                if (throughput[next.dest] < Math.min(throughput[nd.dest], next.cost)) {
                    throughput[next.dest] = Math.min(throughput[nd.dest], next.cost);
                    pq.offer(new Node(next.dest, throughput[next.dest]));
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
        return other.cost - this.cost;
    }
}