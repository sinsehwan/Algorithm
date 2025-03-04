import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] arr;
    static LinkedList<Integer> answerList = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            if(n == 0){
                break;
            }
            arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 6);
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    public static void dfs(int st, int remain) throws IOException {
        if(remain == 0){
            for(int item : answerList){
                bw.write(item + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i = st; i < n; i++){
            answerList.offerLast(arr[i]);
            dfs(i + 1, remain - 1);
            answerList.pollLast();
        }
    }
}
