
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static Set<Integer> notTree = new HashSet<>();

    static int[] parent;
    static int[] nodeRank;

    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        int caseNum = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0) {
                break;
            }
            getInput();

            for(int i = 1; i < graph.size(); i++){
                for(int j = 1; j < graph.get(i).size(); j++){
                    union_root(i, graph.get(i).get(j));
                }
            }

            int answer = 0;
            for(int i = 1; i <= n; i++){
                if(find_root(i) == i && !notTree.contains(i)){
                    answer += 1;
                    //System.out.println(i);
                }
            }

            if (answer == 0){
                bw.write("Case " + caseNum + ": No trees.\n");
            }
            else if (answer == 1){
                bw.write("Case " + caseNum + ": There is one tree.\n");
            }
            else {
                bw.write("Case " + caseNum + ": A forest of " + answer + " trees.\n");
            }

            caseNum += 1;
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        int a, b;
        StringTokenizer st;
        graph = new ArrayList<>();
        notTree = new HashSet<>();

        graph.add(new ArrayList<>());
        for(int i = 0; i < n; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(0);
            graph.add(temp);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            //graph.get(b).add(a);
        }

        parent = new int[n + 1];
        nodeRank = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++){
            parent[i] = i;
            nodeRank[i] = 1;
        }
    }


    public static int find_root(int x){
        if(parent[x] == x){
            return x;
        }

        parent[x] = find_root(parent[x]);
        return parent[x];
    }

    public static void union_root(int x, int y){
        x = find_root(x);
        y = find_root(y);

        if(x == y)
        {
            notTree.add(x);
        }
        else{
            if(nodeRank[x] > nodeRank[y]){
                parent[y] = x;
            }
            else if(nodeRank[y] > nodeRank[x]){
                parent[x] = y;
            }
            else{
                nodeRank[y] += 1;
                parent[x] = y;
            }
        }
    }

}
