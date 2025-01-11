
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n + 1];

        if(n % 2 == 1){
            bw.write("0");
            bw.close();
            br.close();
            return;
        }

        if(n >= 2)
            dp[2] = 3;

        for(int i = 4; i <= n; i += 2){
            dp[i] = dp[i - 2]*3 + 2;
            for(int j = 4; i - j >= 2; j += 2){
                dp[i] += dp[i - j] * 2;
            }
        }

        bw.write(dp[n] + "");

        br.close();
        bw.close();
    }
}
