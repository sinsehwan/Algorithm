import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int answer = Integer.MAX_VALUE;

    static LinkedList<Integer> team = new LinkedList<>();


    public static void main(String[] args) throws IOException{

        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        int n = Integer.parseInt(br.readLine());

        int[][] graph = new int[n][n];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        dfs(0, n, graph);

        bw.write(answer + "");
    }

    void dfs(int st, int n, int[][] graph){
        if(st >= n && team.size() < n/2){
            return;
        }
        if(team.size() == n / 2){
            checkDif(n, graph);
        }

        for(int i = st; i < n; i++){
            team.offerLast(i);
            dfs(i + 1, n, graph);
            team.pollLast();
        }
    }

    void checkDif(int n, int[][] graph){
        boolean[] seperate = new boolean[n];

        for(int item : team){
            seperate[item] = true;
            //System.out.println(item);
        }

        ArrayList<Integer> team1 = new ArrayList<>();
        ArrayList<Integer> team2 = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(seperate[i]){
                team1.add(i);
            }
            else{
                team2.add(i);
            }
        }
        // 팀별 점수
        int s1 = 0, s2 = 0;

        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < n/2; j++){
                s1 += graph[team1.get(i)][team1.get(j)];
                s2 += graph[team2.get(i)][team2.get(j)];
            }
        }

        if(answer > Math.abs(s1 - s2)){
            answer = Math.abs(s1 - s2);
        }
    }
}
