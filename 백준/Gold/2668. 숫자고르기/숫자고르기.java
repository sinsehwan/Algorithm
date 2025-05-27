import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] graph;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        ArrayList<Integer> answerLi = new ArrayList<>();

        boolean[] visited;
        for(int i = 1; i <= n; i++){
            visited = new boolean[n + 1];
            int cur = i;
            int root = i;
            while(!visited[cur]){
                visited[cur] = true;
                cur = graph[cur];
            }

            if(cur == root){
                answerLi.add(cur);
            }
        }

        bw.write(answerLi.size() + "\n");
        for(int item : answerLi){
            bw.write(item + "\n");
        }
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        graph = new int[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = Integer.parseInt(br.readLine());
        }
    }
}
