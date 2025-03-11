import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int v, e, k;
    static LinkedList<Nd>[] adjList;

    public static void main(String[] args) throws IOException {
        getInput();

        dijkstra();

        br.close();
        bw.close();
    }

    static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine()) - 1;

        adjList = new LinkedList[v];
        for(int i = 0; i < v; i++){
            adjList[i] = new LinkedList<>();
        }
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            adjList[a].offerLast(new Nd(b, cost));
        }
    }

    static void dijkstra() throws IOException {
        boolean[] visited = new boolean[v];
        int[] dist = new int[v];

        for(int i = 0; i < v; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        dist[k] = 0;

        PriorityQueue<Nd> pq = new PriorityQueue<>((o1, o2) -> o1.getCost() - o2.getCost());
        pq.offer(new Nd(k, 0));
        visited[k] = true;

        while(!pq.isEmpty()){
            Nd nd = pq.poll();

            for(Nd n : adjList[nd.getIdx()]){
                int checkIdx = n.getIdx();
                int cost = n.getCost();
                if(visited[checkIdx]) continue;

                if(dist[checkIdx] > dist[nd.getIdx()] + cost){
                    dist[checkIdx] = dist[nd.getIdx()] + cost;
                    pq.offer(new Nd(checkIdx, dist[checkIdx]));
                }
            }
        }

        printDist(dist);
    }

    static void printDist(int[] dist) throws IOException{
        for(int item : dist){
            if(item != Integer.MAX_VALUE){
                bw.write(item + "\n");
            }
            else{
                bw.write("INF\n");
            }
        }
    }
}

class Nd{
    private int idx;
    private int cost;

    public Nd(int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }

    public int getIdx() {
        return idx;
    }

    public int getCost(){
        return cost;
    }
}