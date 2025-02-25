import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int start, target;
    static ArrayList<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        getInput();
        init();

        dijkstra(start);

        bw.write(dist[target] + "");
        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n];
        for(int i = 0; i < n; i++){
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, distance));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        target = Integer.parseInt(st.nextToken()) - 1;
    }

    public static void init(){
        dist = new int[n];
        for(int i = 0; i < n; i++){
            dist[i] = Integer.MAX_VALUE / 2;
        }
        visited = new boolean[n];
    }

    public static void dijkstra(int st) {
        dist[st] = 0;
        //n번 반복
        for(int j = 0; j < n; j++){
            int nIdx = -1;
            int nDist = Integer.MAX_VALUE / 2;
            for(int i = 0; i < n; i++){
                if(!visited[i] && dist[i] < nDist){
                    nIdx = i;
                    nDist = dist[i];
                }
            }
            //이미 갈 수 있는 경로는 다 감.
            if(nIdx == -1){
                return;
            }

            visited[nIdx] = true;

            for(Node node : adjList[nIdx]){
                int cIdx = node.getEn();
                int cDist = node.getCost() + nDist;

                if(cDist < dist[cIdx]){
                    dist[cIdx] = cDist;
                }
            }
        }
    }
}

class Node{
    private int en;
    private int cost;

    public Node(int en, int cost) {
        this.en = en;
        this.cost = cost;
    }

    public int getEn() {
        return en;
    }

    public int getCost() {
        return cost;
    }
}
