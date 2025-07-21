import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;

    int[] nodeRank;
    int[] parent;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            unionRoot(a, b);
        }

        int[] timeLine = Arrays.stream(br.readLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int count = 0;
        for(int i = 0; i < n - 1; i++) {
            if(findParent(timeLine[i]) != findParent(timeLine[i + 1])){
                count += 1;
            }
        }

        bw.write(count + "");
    }

    int findParent(int x){
        if(x == parent[x]) return x;

        parent[x] = findParent(parent[x]);
        return parent[x];
    }

    void unionRoot(int x, int y){
        x = findParent(x);
        y = findParent(y);

        if(x != y) {
            if(nodeRank[x] < nodeRank[y]) {
                parent[x] = y;
            }
            else if(nodeRank[y] < nodeRank[x]) {
                parent[y] = x;
            }
            else{
                parent[y] = x;
                nodeRank[x] += 1;
            }
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nodeRank = new int[n + 1];
        parent = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            nodeRank[i] = 1;
            parent[i] = i;
        }
    }
}
