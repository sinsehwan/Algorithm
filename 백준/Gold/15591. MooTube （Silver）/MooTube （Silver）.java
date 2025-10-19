import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    ArrayList<ArrayList<Edge>> edgeList = new ArrayList<>();

    int n, q;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ki = Integer.parseInt(st.nextToken());
            int vi = Integer.parseInt(st.nextToken());

            bw.write(bfs(vi, ki) + "\n");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            edgeList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edgeList.get(a).add(new Edge(b, c));
            edgeList.get(b).add(new Edge(a, c));
        }


    }

    int bfs(int st, int minK) {
        boolean[] visited = new boolean[n + 1];
        int answer = 0;
        LinkedList<Edge> queue = new LinkedList<>();
        visited[st] = true;
        queue.offerLast(new Edge(st, 0));

        while(!queue.isEmpty()) {
            int qSize = queue.size();

            for (int i = 0; i < qSize; i++) {
                Edge nd = queue.pollFirst();

                for (Edge next : edgeList.get(nd.dest)) {
                    if (!visited[next.dest] && next.cost >= minK) {
                        queue.offerLast(next);
                        visited[next.dest] = true;
                        answer += 1;
                    }

                }
            }
        }
        return answer;
    }
}

class Edge {
    int dest;
    int cost;

    public Edge(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}

