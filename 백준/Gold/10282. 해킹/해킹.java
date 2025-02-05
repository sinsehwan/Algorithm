import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, d, c;

    static ArrayList<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < t; i++){
            getInput();
            init();

            dijkstra(c);

            int num = 0;
            int lastTime = 0;
            for(int j = 1; j <= n; j++){
                if(dist[j] != Integer.MAX_VALUE){
                    num += 1;
                    if(lastTime <  dist[j]){
                        lastTime = dist[j];
                    }
                }
            }
            bw.write(num + " " + lastTime + "\n");
        }

        br.close();
        bw.close();
    }

    private static void getInput() throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];

        for(int j = 0; j <= n; j++){
            adjList[j] = new ArrayList<>();
        }

        for(int j = 0; j < d; j++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[b].add(new Node(a, cost));
        }
    }

    public static void init(){
        visited = new boolean[n + 1];
        dist = new int[n + 1];

        for(int i = 0; i <= n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
    }

    public static void dijkstra(int st){
        dist[st] = 0;

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
                int updateIdx = adjList[nIdx].get(j).getIdx();
                int updateDist = nDist + adjList[nIdx].get(j).getDist();

                if(updateDist < dist[updateIdx]){
                    dist[updateIdx] = updateDist;
                }
            }
        }
    }
}

class Node{
    private int idx;
    private int dist;

    public Node(int idx, int dist){
        this.idx = idx;
        this.dist = dist;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}
