import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int[] move;
    boolean[] visited;

    int[] vec = {1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        bw.write(bfs(1) + "");
    }

    int bfs(int st){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offerLast(st);
        visited[st] = true;

        int count = 0;

        while(!queue.isEmpty()) {
            int qSize = queue.size();
            for(int i = 0; i < qSize; i++) {
                int p = queue.pollFirst();

                if (p == 100) {
                    return count;
                }

                for (int v : vec) {
                    int next = p + v;
                    if (next > 100) break;

                    if (!visited[next]) {
                        visited[next] = true;
                        if (move[next] != 0) {
                            visited[move[next]] = true;
                            queue.offerLast(move[next]);
                        } else {
                            queue.offerLast(next);
                        }
                    }
                }
            }
            count += 1;
        }

        return -1;
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        move = new int[101];
        visited = new boolean[101];

        for(int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            move[a] = b;
        }
    }
}
