import java.io.*;
import java.util.*;


/**
 * 모든 지점에서 벨만 포드를 수행해봐야 하나
 *
 * 음수 사이클이 있더라도 그 사이클의 출발점으로 도달하는 경로가 없다면
 * 불가능한 거니까.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(s, e, cost));
            edges.add(new Edge(e, s, cost));
        }
        for(int i = 0; i < w; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(s, e, -cost));
        }

        boolean flag = bellmanFold(1, n, edges);

        if(flag){
            bw.write("YES\n");
        }
        else{
            bw.write("NO\n");
        }
    }

    boolean bellmanFold(int st, int n, ArrayList<Edge> edges) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[st] = 0;

        boolean flag = false;
        for(int i = 1; i <= n; i++){
            for(Edge e : edges){
                // 연결돼 있지 않은 지점을 Integer.MAX_VALUE / 2의 값으로 연결시켜서
                //강제적으로 연결 그래프를 만들고
                //여기에서 음의 사이클이 존재하는지 확인한다.

                if(dist[e.end] > dist[e.start] + e.cost){
                    dist[e.end] = dist[e.start] + e.cost;

                    if(i == n){
                        flag = true;
                    }
                }
            }
        }

        return flag;
    }
}

class Edge {
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost){
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}
