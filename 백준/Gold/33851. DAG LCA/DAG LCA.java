import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    ArrayList<Query> queries = new ArrayList<>();

    int n, m ,q;
    int[] distU, distV;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        getInput();

        for(Query q : queries){
            bw.write(findBest(q) + "\n");
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 역방향 그래프 생성
            graph.get(b).add(a);
        }

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            queries.add(new Query(a, b));
        }

        distU = new int[n + 1];
        distV = new int[n + 1];
    }

    int findBest(Query q){
        for(int i = 1; i <= n; i++){
            distU[i] = Integer.MAX_VALUE;
            distV[i] = Integer.MAX_VALUE;
        }
        boolean[] visited = new boolean[n + 1];

        LinkedList<Integer> queueU = new LinkedList<>();
        LinkedList<Integer> queueV = new LinkedList<>();

        queueU.offerLast(q.u);
        queueV.offerLast(q.v);

        visited[q.u] = true;
        bfs(queueU, distU, visited);
        visited = new boolean[n + 1];
        visited[q.v] = true;
        bfs(queueV, distV, visited);

        int best = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            if(distU[i] != Integer.MAX_VALUE && distV[i] != Integer.MAX_VALUE){
                best = Math.min(best, Math.max(distU[i], distV[i]));
            }
        }
        if(best == Integer.MAX_VALUE) return -1;
        return best;
    }

    void bfs(LinkedList<Integer> queue, int[] dist, boolean[] visited){
        int count = 0;
        while(!queue.isEmpty()) {
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++){
                int n = queue.pollFirst();

                dist[n] = count;
                for (int next : graph.get(n)){
                    if(!visited[next]) {
                        queue.offerLast(next);
                        visited[next] = true;
                    }
                }
            }
            count += 1;
        }
    }
}

class Query{
    int u, v;

    Query(int u, int v){
        this.u = u;
        this.v = v;
    }
}