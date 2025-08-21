import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    boolean[] isCandidate;

    int n, m, t;
    int s, g, h;
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int[] distFromS = new int[n + 1];
        int[] distFromG = new int[n + 1];
        int[] distFromH = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            distFromS[i] = Integer.MAX_VALUE / 3;
            distFromG[i] = Integer.MAX_VALUE / 3;
            distFromH[i] = Integer.MAX_VALUE / 3;
        }

        dijkstra(s, distFromS);
        dijkstra(g, distFromG);
        dijkstra(h, distFromH);

        for(int i = 1; i <= n; i++) {
            if(isCandidate[i]) {
                if(distFromS[i] == distFromS[g] + distFromG[h] + distFromH[i] ||
                distFromS[i] == distFromS[h] + distFromH[g] + distFromG[i]) {
                    bw.write(i + " ");
                }
            }
        }
        bw.write("\n");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        graph.clear();
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
        isCandidate = new boolean[n + 1];
        for(int i = 0; i < t; i++) {
            int idx = Integer.parseInt(br.readLine());
            isCandidate[idx] = true;
        }
    }

    void dijkstra(int st, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(st, 0));
        dist[st] = 0;

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