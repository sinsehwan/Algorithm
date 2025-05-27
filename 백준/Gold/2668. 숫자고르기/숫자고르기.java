import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] graph;
    boolean[] visited;
    boolean[] finished;
    List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for (int i = 1; i <= n; i++) {
            if(!visited[i]){
                dfs(i, new ArrayList<>());
            }
        }

        bw.write(answer.size() + "\n");
        Collections.sort(answer);
        for(int item : answer){
            bw.write(item + "\n");
        }
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        graph = new int[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[n + 1];
        finished = new boolean[n + 1];
    }

    void dfs(int cur, List<Integer> path) {
        visited[cur] = true;
        path.add(cur);

        int next = graph[cur];
        if(!visited[next]){
            dfs(next, path);
        }
        else if(!finished[next]){
            // 사이클 발견한 경우
            for(int i = next; i != cur; i = graph[i]){
                answer.add(i);
            }
            answer.add(cur);
        }

        finished[cur] = true;
    }
}
