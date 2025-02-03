import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean[] isFix;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int fixNum = Integer.parseInt(br.readLine());

        isFix = new boolean[n + 1];

        for(int i = 0; i < fixNum; i++){
            isFix[Integer.parseInt(br.readLine())] = true;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        if(n >= 2){
            if(isFix[1] || isFix[2]){
                dp[2] = 1;
            }
            else{
                dp[2] = 2;
            }
        }
        for(int i = 3; i <= n; i++){
            if(isFix[i] || isFix[i - 1]){
                dp[i] = dp[i - 1];
            }
            else{
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        bw.write(dp[n] + "");


        br.close();
        bw.close();
    }

}
