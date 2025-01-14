
import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static ArrayList<Tuple<Integer, Integer>> graph = new ArrayList<>();

    static int[] dp;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        dp = new int[n];

        for(int i = 0; i < n; i++){
            dp[i] = 1;
        }

        int height, weight;
        for(int i = 0;i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            weight = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            graph.add(new Tuple<>(height, weight));
        }

        for(int i = 0; i < n; i++){ // 검증 대상
            for(int j = 0; j < n; j++){ // 비교군
                Tuple<Integer, Integer> target = graph.get(i);
                Tuple<Integer, Integer> test = graph.get(j);
                if(target.getLeft() < test.getLeft() && target.getRight() < test.getRight()){
                    dp[i] += 1;
                }
            }
        }

        for(int item : dp){
            bw.write(item + " ");
        }

        br.close();
        bw.close();
    }

}

class Tuple<A, B>{
    private A left;
    private B right;

    public Tuple(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public B getRight() {
        return right;
    }

    public A getLeft() {
        return left;
    }

    public void setLeft(A left) {
        this.left = left;
    }

    public void setRight(B right) {
        this.right = right;
    }
}
