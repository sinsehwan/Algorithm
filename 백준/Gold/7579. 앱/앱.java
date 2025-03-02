import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] cost;
    static int[] memory;

    static int[] dp;

    static int totalCost;

    public static void main(String[] args) throws IOException {
        getInput();

        knapsack();

        for(int i = 0; i <= totalCost; i++){
            if(dp[i] >= m){
                bw.write(i + "");
                break;
            }
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memory = new int[n];
        cost = new int[n];

        memory = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        cost = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        for(int c : cost){
            totalCost += c;
        }
        dp = new int[totalCost + 1];
    }

    public static void knapsack(){
        for(int i = 0; i < n; i++){
            for(int j = totalCost; j >= cost[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }
    }
}
