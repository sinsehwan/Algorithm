import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static int[][] graph;
    static ArrayList<Node> chickens = new ArrayList<>();
    static ArrayList<Node> houses = new ArrayList<>();
    static LinkedList<Node> available = new LinkedList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        getInput();

        dfs(0, m);

        bw.write(answer + "");
    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][n];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] == 1){
                    houses.add(new Node(i, j));
                }
                else if(graph[i][j] == 2){
                    chickens.add(new Node(i, j));
                }
            }
        }
    }

    void dfs(int st, int remain) throws IOException{
        if(remain == 0){
            check();
            return;
        }

        for(int i = st; i < chickens.size(); i++){
            available.offerLast(chickens.get(i));
            dfs(i + 1, remain - 1);
            available.pollLast();
        }
    }

    void check() throws IOException {
        int total = 0;
        for(Node house : houses){
            int best = Integer.MAX_VALUE;
            for(Node chicken : available){
                int dist = Math.abs(house.y - chicken.y) + Math.abs(house.x - chicken.x);
                best = Math.min(dist, best);
            }
            //bw.write("house : " + house.y + house.x + ", best : " + best + "\n");

            total += best;
        }
        if(answer > total){
            answer = total;
        }
    }
}


class Node{
    int y, x;

    public Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}