import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr;
    static Set<String> answerSet = new LinkedHashSet<>();
    static LinkedList<Integer> answerList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        dfs(0, m);

        for(String data : answerSet){
            bw.write(data + "\n");
        }
        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);
    }

    public static void dfs(int st, int remain){
        if(remain == 0){
            StringBuilder sb = new StringBuilder();
            for(int item : answerList){
                sb.append(item).append(" ");
            }
            answerSet.add(sb.toString());
            return;
        }
        else if(st >= n){
            return;
        }

        for(int i = st; i < n; i++){
            answerList.offerLast(arr[i]);
            dfs(i + 1, remain - 1);
            answerList.pollLast();
        }
    }
}
