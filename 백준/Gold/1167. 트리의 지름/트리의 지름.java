
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static boolean[] isVisited;

    static int maxDistance;
    static int maxNode;

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        getInput();

        dfs(1, 0);

        isVisited = new boolean[n + 1];
        maxDistance = 0;
        //System.out.println("maxNode : " + maxNode);
        dfs(maxNode, 0);

        bw.write(maxDistance + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        isVisited = new boolean[n + 1];

        StringTokenizer st;

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            while(true){
                int nextNode = Integer.parseInt(st.nextToken());

                if(nextNode == -1){
                    break;
                }

                else{
                    int distance = Integer.parseInt(st.nextToken());
                    graph.get(index).add(new Node(nextNode, distance));
                }
            }
        }
    }

    public static void dfs(int start, int preDistance){
        isVisited[start] = true;

        if(preDistance > maxDistance){
            maxDistance = preDistance;
            maxNode = start;
        }

        for(Node node : graph.get(start)){
            if(!isVisited[node.getNextNode()]){
                int nextDistance = node.getDistance() + preDistance;
                //System.out.println("dfs : " + node.getNextNode() + ", " + nextDistance);

                dfs(node.getNextNode(), nextDistance);
            }
        }
    }
}

class Node{
    private int nextNode;
    private int distance;

    public Node(int nextNode, int distance) {
        this.nextNode = nextNode;
        this.distance = distance;
    }

    public int getNextNode() {
        return nextNode;
    }

    public void setNextNode(int nextNode) {
        this.nextNode = nextNode;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
