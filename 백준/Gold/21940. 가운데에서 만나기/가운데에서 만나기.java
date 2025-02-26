import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, k;
    static int[][] graph;
    static int[] friends;
    static LinkedList<Integer> answerList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        floyd();

        calAnswer();

        for(int item : answerList){
            bw.write(item + " ");
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        init();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            if(graph[a][b] > cost){
                graph[a][b] = cost;
            }
        }

        k = Integer.parseInt(br.readLine());
        friends = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        for(int i = 0; i < k; i++){
            friends[i] -= 1;
        }
    }

    public static void init(){
        graph = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                graph[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for(int i = 0; i < n; i++){
            graph[i][i] = 0;
        }
    }

    public static void floyd(){
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    public static void calAnswer(){
        int bestCost = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            int cityCost = getWorstCost(i);
            if(cityCost < bestCost){
                bestCost = cityCost;
                answerList.clear();
                answerList.offerLast(i + 1);
            }
            else if(cityCost == bestCost){
                answerList.offerLast(i + 1);
            }
        }
    }

    public static int getWorstCost(int city){
        int worst = 0;
        for(int f : friends){
            int cost = get2WayCost(f, city);
            if(cost > worst){
                worst = cost;
            }
        }
        return worst;
    }

    public static int get2WayCost(int st, int en){
        int cost = 0;
        cost += graph[st][en];
        cost += graph[en][st];
        return cost;
    }
}
