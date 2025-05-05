import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] arr;
    int[] dp;


    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < n; i++){
            dp[i] = arr[i];
        }
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < i; j++){
                    if(arr[j] < arr[i]) {
                        dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                    }
                }
            }
        }

        int best = 0;
        for(int item : dp){
            if(item > best){
                best = item;
            }
        }
        bw.write(best + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        dp = new int[n];
    }
}
