import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;

    int[][] graph;

    static ArrayList<Nd> chickens = new ArrayList<>();

    static ArrayList<Nd> houses = new ArrayList<>();
    static LinkedList<Nd> available = new LinkedList<>();

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dfs(0, m);

        bw.write(answer + "");
    }

    void getInput() throws IOException {
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
                    houses.add(new Nd(i, j));
                }
                else if(graph[i][j] == 2) {
                    chickens.add(new Nd(i, j));
                }
            }
        }
    }

    void dfs(int st, int remain) throws IOException {
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
        for(Nd house : houses) {
            int best = Integer.MAX_VALUE;
            for(Nd chicken : available){
                int dist = Math.abs(house.y - chicken.y) + Math.abs(house.x - chicken.x);
                best = Math.min(dist, best);
            }

            total += best;
        }
        if(answer > total){
            answer = total;
        }
    }
}

class Nd{
    int y, x;

    public Nd(int y, int x){
        this.y = y;
        this.x = x;
    }
}