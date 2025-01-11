
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++){
            dp[i] = arr[i];

            for(int j = 1; j <= i/2; j++){
                if(dp[i] < dp[j] + dp[i - j]){
                    dp[i] = dp[j] + dp[i - j];
                }
            }
        }
        bw.write(dp[n] + "");


        br.close();
        bw.close();
    }

}
