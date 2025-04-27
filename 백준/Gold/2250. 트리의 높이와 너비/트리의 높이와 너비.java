import java.io.*;
import java.util.*;
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    Map<Integer, TreeNode> tree = new HashMap<>();

    int place = 1;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        // 루트 찾기
        int root = findRoot();

        // 중위 순회 수행. place값 갱신
        inOrder(root);
        // 가장 너비 넓은 것 구하기 - bfs

        bfs(root);
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int lIdx = Integer.parseInt(st.nextToken());
            int rIdx = Integer.parseInt(st.nextToken());

            tree.put(value, new TreeNode(value, lIdx, rIdx));
        }
    }

    int findRoot(){
        boolean[] isRoot = new boolean[n + 1];

        for (int i = 1; i <= n; i++){
            isRoot[i] = true;
        }

        for(TreeNode node : tree.values()){
            if(node.lIdx != -1) {
                isRoot[node.lIdx] = false;
            }
            if(node.rIdx != -1) {
                isRoot[node.rIdx] = false;
            }
        }

        for(int i = 1; i <= n; i++){
            if(isRoot[i]){
                return i;
            }
        }
        return -1;
    }

    void inOrder(int idx){
        TreeNode node = tree.get(idx);

        if(node.lIdx != -1) {
            inOrder(node.lIdx);
        }
        node.place = place;
        place += 1;
        if(node.rIdx != -1) {
            inOrder(node.rIdx);
        }
    }

    void bfs(int st) throws IOException{
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(tree.get(st));

        int best = 0;
        int bestLevel = -1;
        int count = 1;

        while(!queue.isEmpty()){
            int qSize = queue.size();

            int min = n;
            int max = 1;

            for(int i = 0; i < qSize; i++){
                TreeNode node = queue.pollFirst();

                if(node.place < min){
                    min = node.place;
                }
                if(node.place > max){
                    max = node.place;
                }

                if(node.lIdx != -1){
                    queue.offerLast(tree.get(node.lIdx));
                }
                if(node.rIdx != -1){
                    queue.offerLast(tree.get(node.rIdx));
                }
            }

            int dist = max - min + 1;
            if(dist > best){
                best = dist;
                bestLevel = count;
            }
            count += 1;
        }

        bw.write(bestLevel + " " + best);
    }
}

class TreeNode{
    int value;
    int place;
    int lIdx;
    int rIdx;

    public TreeNode(int value, int lIdx, int rIdx){
        this.value = value;
        this.lIdx = lIdx;
        this.rIdx = rIdx;
    }
}