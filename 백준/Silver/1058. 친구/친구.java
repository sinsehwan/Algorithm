import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    ArrayList<ArrayList<Integer>> friends = new ArrayList<>();

    int n;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int best = 0;

        for(int i = 1; i <= n; i++) {
            best = Math.max(best, bfs(i));
        }

        bw.write(best + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i <= n; i++) {
            friends.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++) {
            String line = br.readLine();

            for(int j = 1; j <= n; j++) {
                if(line.charAt(j - 1) == 'Y'){
                    friends.get(i).add(j);
                }
            }
        }
    }

    int bfs(int st) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offerLast(st);

        boolean[] visited = new boolean[n + 1];
        visited[st] = true;
        int num = 0;

        for(int i = 0; i < 2; i++) {
            int qSize = queue.size();

            for(int j = 0; j < qSize; j++) {
                int t = queue.pollFirst();

                for(int n : friends.get(t)) {
                    if(!visited[n]) {
                        visited[n] = true;
                        queue.offerLast(n);
                        num += 1;
                    }
                }
            }
        }

        return num;
    }
}
