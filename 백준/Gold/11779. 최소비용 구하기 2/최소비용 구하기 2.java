import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int start, end;

    static ArrayList<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;

    static ArrayList<Integer>[] midPoint;

    public static void main(String[] args) throws IOException {
        getInput();

        init();

        dijkstra();
        /*
        int idx = 0;
        int cost = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            if(dist[i] < cost){
                cost = dist[i];
                idx = i;
            }
        }

         */
        //Collections.sort(midPoint[end]);
        bw.write(dist[end] + "\n");
        bw.write(midPoint[end].size() +"\n");
        for(int item : midPoint[end]){
            bw.write(item + " ");
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        midPoint = new ArrayList[n + 1];
        adjList = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++){
            adjList[i] = new ArrayList<>();
            midPoint[i] = new ArrayList<>();
            midPoint[i].add(i);
        }
        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    public static void init(){
        dist = new int[n + 1];
        for(int i = 0; i <= n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        visited = new boolean[n + 1];
    }

    public static void dijkstra() {
        dist[start] = 0;

        for(int i = 0; i < n; i++){
            int nIdx = 0;
            int nDist = Integer.MAX_VALUE;

            for(int j = 1; j <= n; j++){
                if(!visited[j] && dist[j] < nDist){
                    nIdx = j;
                    nDist = dist[j];
                }
            }

            visited[nIdx] = true;

            for(int j = 0; j < adjList[nIdx].size(); j++){
                int checkIdx = adjList[nIdx].get(j).getIdx();
                int cost = nDist + adjList[nIdx].get(j).getCost();

                if(dist[checkIdx] > cost){
                    dist[checkIdx] = cost;
                    midPoint[checkIdx] = new ArrayList<>(midPoint[nIdx]);
                    midPoint[checkIdx].add(checkIdx);
                }
            }
        }
    }
}

class Node{
    private int idx;
    private int cost;

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    public int getIdx() {
        return idx;
    }

    public int getCost() {
        return cost;
    }
}
