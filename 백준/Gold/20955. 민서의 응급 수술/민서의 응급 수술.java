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

        int cycleCount = 0;
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(findRoot(a) == findRoot(b)){
                cycleCount += 1;
            }
            else {
                unionRoot(a, b);
            }
        }

        int count = 0;
        for(int i = 2; i <= n; i++) {
            if(findRoot(1) != findRoot(i)){
                unionRoot(1, i);
                count += 1;
            }
        }
        count += cycleCount;
        bw.write(count + "");
    }

    int findRoot(int x){
        if(parent[x] == x) return x;

        parent[x] = findRoot(parent[x]);

        return parent[x];
    }

    void unionRoot(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if(x != y) {
            if(nodeRank[x] < nodeRank[y]){
                parent[x] = y;
            }
            else if(nodeRank[y] < nodeRank[x]){
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

        for(int i = 1; i <= n; i++){
            nodeRank[i] = 1;
            parent[i] = i;
        }
    }
}

