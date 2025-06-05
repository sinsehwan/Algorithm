import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m, k;
    // 아이들 집단의 사탕 수(root만 의미 있음)
    int[] candy;
    // 엮인 아이들 수
    int[] sz;
    //union-find
    int[] parent;
    int[] nodeRank;

    boolean[] visited;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        // 배낭 문제(knapsack)로 접근

        int[] dp = new int[k];
        ArrayList<Integer> cost = new ArrayList<>();
        ArrayList<Integer> value = new ArrayList<>();

        // root만 추가
        for(int i = 0; i < n; i++){
            int t = findRoot(i);
            if(!visited[t]){
                cost.add(sz[t]);
                value.add(candy[t]);

                visited[t] = true;
            }
        }

        int tn = value.size();

        for(int i = 0; i < tn; i++){
            for(int j = k - 1; j >= cost.get(i); j--){
                dp[j] = Math.max(dp[j - cost.get(i)] + value.get(i), dp[j]);
            }
        }

        bw.write(dp[k - 1] + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        candy = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        sz = new int[n];
        parent = new int[n];
        nodeRank = new int[n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
            nodeRank[i] = 1;
            sz[i] = 1;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            unionRoot(a, b);
        }
    }

    int findRoot(int x){
        if(x == parent[x]) return x;

        parent[x] = findRoot(parent[x]);
        return parent[x];
    }

    void unionRoot(int x, int y){
        x = findRoot(x);
        y = findRoot(y);

        if(x != y){
            if(nodeRank[x] > nodeRank[y]){
                parent[y] = x;
                candy[x] += candy[y];
                sz[x] += sz[y];
            }
            else if(nodeRank[y] > nodeRank[x]){
                parent[x] = y;
                candy[y] += candy[x];
                sz[y] += sz[x];
            }
            else{
                parent[y] = x;
                nodeRank[x] += 1;
                candy[x] += candy[y];
                sz[x] += sz[y];
            }
        }
    }
}

class Target{
    int candyNum;
    int childNum;

    Target(int candyNum, int childNum){
        this.candyNum = candyNum;
        this.childNum = childNum;
    }
}
