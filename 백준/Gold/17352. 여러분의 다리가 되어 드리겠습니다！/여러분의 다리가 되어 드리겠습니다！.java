import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] nodeRank;
    int[] parent;
    int[][] bridges;

    public static void main(String[] args) throws IOException{
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n - 2; i++) {
            unionRoot(bridges[i][0], bridges[i][1]);
        }

        for(int i = 2; i <= n; i++) {
            if(findRoot(1) != findRoot(i)){
                bw.write(1 + " " + i);
                return;
            }
        }
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        bridges = new int[n - 2][2];

        for(int i = 0; i < n - 2; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                bridges[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nodeRank = new int[n + 1];
        parent = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            nodeRank[i] = 1;
            parent[i] = i;
        }
    }

    int findRoot(int x) {
        if(parent[x] == x) return x;

        parent[x] = findRoot(parent[x]);
        return parent[x];
    }

    void unionRoot(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);

        if(a != b) {
            if (nodeRank[a] < nodeRank[b]) {
                parent[a] = b;
            }
            else if(nodeRank[a] > nodeRank[b]){
                parent[b] = a;
            }
            else{
                parent[b] = a;
                nodeRank[a] += 1;
            }
        }
    }
}
