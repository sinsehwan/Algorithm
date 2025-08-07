import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int t;
    int n;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            new Main().solve();
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        bfs(n);
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
    }

    void bfs(int num) throws IOException {
        LinkedList<String> queue = new LinkedList<>();

        queue.offerLast("1");
        for(int i = 2; i <= num; i++) {
            int qSize = queue.size();

            for(int j = 0; j < qSize; j++) {
                String cur = queue.pollFirst();

                queue.offerLast(cur + " " + i);
                queue.offerLast(cur + "+" + i);
                queue.offerLast(cur + "-" + i);
            }
        }

        while(!queue.isEmpty()) {
            String cur = queue.pollFirst();
            String cur2 = cur.replace(" ", "");

            StringTokenizer st = new StringTokenizer(cur2, "+-", true);

            int value = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                String op = st.nextToken();
                int next = Integer.parseInt(st.nextToken());

                if(op.charAt(0) == '+') {
                    value += next;
                }
                else{
                    value -= next;
                }
            }

            if(value == 0) {
                bw.write(cur + "\n");
            }
        }
    }
}
