import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;

    int[] parent;
    int[] nodeRank;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        nodeRank = new int[n + 1];

        for(int i = 1; i <= n; i++){
            parent[i] = i;
            nodeRank[i] = 1;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(mode == 0){
                unionRoot(a, b);
            }
            else{
                if(findRoot(a) == findRoot(b)){
                    bw.write("YES\n");
                }
                else{
                    bw.write("NO\n");
                }
            }
        }
    }

    public int findRoot(int x) {
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
            else if(nodeRank[y] > nodeRank[x]){
                parent[x] = y;
            }
            else{
                parent[y] = x;
                nodeRank[x] += 1;
            }
        }
    }
}
