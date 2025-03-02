import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, t;
    static int[] weight;
    static int[] value;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        getInput();

        knapsack();

        bw.write(dp[t] + "");


        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        weight = new int[n];
        value = new int[n];
        dp = new int[t + 1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void knapsack(){
        // i번 물건 고려
        for(int i = 0; i < n; i++){
            // dp[j] : 무게 j까지 감당가능할 때 가능한 최댓값
            for(int j = t; j >= weight[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
    }
}
