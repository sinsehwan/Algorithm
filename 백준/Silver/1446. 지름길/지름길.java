import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, d;
    static int[] dist;
    static boolean[] visited;
    static LinkedList<Node>[] adjList;


    public static void main(String[] args) throws IOException {
        getInput();

        init();

        dijkstra();
        /*
        for(int i = 0; i <= d; i++){
            bw.write(dist[i] + "\n");
        }
         */

        bw.write(dist[d] + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        adjList = new LinkedList[d + 1];
        for(int i = 0; i <= d; i++){
            adjList[i] = new LinkedList<>();
        }

        for(int i = 0; i <= d; i++){
            for(int j = i + 1; j <= d; j++) {
                adjList[i].offerLast(new Node(j, j - i));
            }
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(b > d){
                continue;
            }
            adjList[a].offerLast(new Node(b, cost));
        }
    }

    public static void init(){
        dist = new int[d + 1];

        for(int i = 1; i <= d; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        visited = new boolean[d + 1];
    }

    public static void dijkstra(){
        dist[0] = 0;

        for(int i = 0; i <= d; i++){
            int nIdx = 0;
            int nDist = Integer.MAX_VALUE;

            for(int j = 0; j <= d; j++){
                if(!visited[j] && dist[j] < nDist){
                    nIdx = j;
                    nDist = dist[j];
                }
            }

            visited[nIdx] = true;

            for(int j = 0; j < adjList[nIdx].size(); j++){
                int checkIdx = adjList[nIdx].get(j).getIdx();
                int cost = adjList[nIdx].get(j).getCost();

                if(dist[checkIdx] > cost + dist[nIdx]){
                    dist[checkIdx] = cost + dist[nIdx];
                }
            }
        }
    }
}

class Node{
    private int idx;
    private int cost;

    public Node(int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }

    public int getIdx(){
        return idx;
    }

    public int getCost(){
        return cost;
    }
}

