
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, pNum;

    static Set<Integer> truthList = new HashSet<>();

    static ArrayList<ArrayList<Integer>> party = new ArrayList<>();

    static int[] parent;

    static int[] node_rank;

    public static void main(String[] args) throws IOException{
        getInput();

        //인덱스 1부터 시작
        party.add(new ArrayList<>());

        parent = new int[n + 1];
        node_rank = new int[n + 1];

        //node_rank[0]에 속한 요소가 truthList에 속하는 요소임
        node_rank[0] = 51;
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }

        for (Integer i : truthList) {
            union_root(0, i);
        }

        for(int i = 0; i < pNum; i++){
            for(int j = 0; j < party.get(i).size() - 1; j++){
                int a = party.get(i).get(j);
                int b = party.get(i).get(j + 1);

                union_root(a, b);
            }
        }

        // parent가 0인 요소가 없는 party만 카운트
        int count = 0;
        for(int i = 0; i < pNum; i++){
            boolean flag = true;
            for(int j = 0; j < party.get(i).size(); j++){
                if(find_root(party.get(i).get(j)) == 0){
                    flag = false;
                    break;
                }
            }

            if(flag)
                count += 1;
        }

        bw.write(count + "");

        br.close();
        bw.close();
    }


    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        pNum = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int tNum = Integer.parseInt(st.nextToken());

        for(int i = 0; i < tNum; i++){
            truthList.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < pNum; i++){
            st = new StringTokenizer(br.readLine());

            int participants = Integer.parseInt(st.nextToken());

            ArrayList<Integer> pt = new ArrayList<>();
            for(int j = 0; j < participants; j++){
                pt.add(Integer.parseInt(st.nextToken()));
            }
            party.add(pt);
        }
    }

    public static int find_root(int x){
        if(x == parent[x]){
            return x;
        }

        parent[x] = find_root(parent[x]);
        return parent[x];
    }

    public static void union_root(int x, int y){
        x = find_root(x);
        y = find_root(y);

        if(x != y){
            if(node_rank[x] > node_rank[y]){
                parent[y] = x;
            }
            else if(node_rank[x] < node_rank[y]){
                parent[x] = y;
            }
            else {
                parent[x] = y;
                node_rank[x] += 1;
            }
        }
    }
}
