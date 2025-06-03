import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n, k, m;

    int[] parent;
    int[] nodeRank;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 1; i <= t; i++){
            bw.write("Scenario " + i + ":\n");
            new Main().solve();
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        parent = new int[n];
        nodeRank = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
            nodeRank[i] = 1;
        }

        k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            unionRoot(a, b);
        }
        m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(findRoot(a) == findRoot(b)){
                bw.write("1\n");
            }
            else{
                bw.write("0\n");
            }
        }
    }

    public int findRoot(int x){
        if(x == parent[x]) return x;

        parent[x] = findRoot(parent[x]);
        return parent[x];
    }

    public void unionRoot(int x, int y){
        x = findRoot(x);
        y = findRoot(y);

        if(x != y){
            if(nodeRank[x] > nodeRank[y]){
                parent[y] = x;
            }
            else if(nodeRank[x] < nodeRank[y]){
                parent[x] = y;
            }
            else{
                parent[y] = x;
                nodeRank[x] += 1;
            }
        }
    }
}
