import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, e;
    static int v1, v2;

    static ArrayList<Node>[] adjList;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer1 = -1;
        int answer2 = -1;
        reset();
        int d1 = dijkstra(1, v1);
        reset();
        int d2 = dijkstra(v1, v2);
        reset();
        int d3 = dijkstra(v2, n);
        if(d1 != Integer.MAX_VALUE && d2 != Integer.MAX_VALUE && d3 != Integer.MAX_VALUE){
            answer1 = d1 + d2 + d3;
        }
        reset();
        d1 = dijkstra(1, v2);
        reset();
        d2 = dijkstra(v2, v1);
        reset();
        d3 = dijkstra(v1, n);
        if(d1 != Integer.MAX_VALUE && d2 != Integer.MAX_VALUE && d3 != Integer.MAX_VALUE){
            answer2 = d1 + d2 + d3;
        }

        //answer1이 -1이면 answer2도 -1
        int answer = Math.min(answer1, answer2);
        bw.write(answer + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++){
            adjList[i] = new ArrayList<>();
        }
        // 양방향 간선 추가
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, cost));
            adjList[b].add(new Node(a, cost));
        }

        st = new StringTokenizer(br.readLine());

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    public static void reset(){
        visited = new boolean[n + 1];
        dist = new int[n + 1];

        for(int i = 0; i <= n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
    }

    public static int dijkstra(int st, int en){
        dist[st] = 0;

        //정점 개수만큼 반복
        for(int i = 1; i <= n; i++){
            int nIdx = 0;
            int nDist = Integer.MAX_VALUE;

            for(int j = 1; j <= n; j++){
                if(dist[j] < nDist && !visited[j]){
                    nIdx = j;
                    nDist = dist[j];
                }
            }
            visited[nIdx] = true;
            for(int j = 0; j < adjList[nIdx].size(); j++){
                int updateIdx = adjList[nIdx].get(j).getIdx();
                int betweenCost = adjList[nIdx].get(j).getDistance();
                // 탐색 노드의 인접 노드 중 더 최적의 경로 있으면 업데이트
                if(dist[updateIdx] > nDist + betweenCost){
                    dist[updateIdx] = nDist + betweenCost;
                }
            }
        }
        //System.out.println(Arrays.toString(dist));
        return dist[en];
    }
}

class Node{
    private int distance;
    private int idx;

    public Node(int idx, int distance){
        this.idx = idx;
        this.distance = distance;
    }

    public int getIdx() {
        return idx;
    }

    public int getDistance(){
        return distance;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public void setIdx(int idx){
        this.idx = idx;
    }
}
