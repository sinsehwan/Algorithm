import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr;

    static LinkedList<Integer> answerList = new LinkedList<>();
    static Set<String> answerSet = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        getInput();

        dfs(m);

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

    public static void dfs(int remain){
        if(remain == 0) {
            StringBuilder sb = new StringBuilder();
            for(int item : answerList){
                sb.append(item).append(" ");
            }
            answerSet.add(sb.toString());
            return;
        }

        for(int i = 0; i < n; i++){
            answerList.offerLast(arr[i]);
            dfs(remain - 1);
            answerList.pollLast();
        }
    }
}
