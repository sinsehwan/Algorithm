
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[10];
        long[] dpNext = new long[10];

        dp[0] = 0;
        for(int i = 1; i < 10; i++){
            dp[i] = 1;
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < 10; j++){
                if(j == 0){
                    dpNext[j] = dp[1];
                } else if (j == 9){
                    dpNext[j] = dp[8];
                }
                else{
                    dpNext[j] = (dp[j - 1] + dp[j + 1]) % 1000000000;
                }
            }

            for(int j = 0; j < 10; j++){
                dp[j] = dpNext[j];
            }
        }

        long answer = 0;
        for(long item : dp){
            answer += item;
            answer %= 1000000000;
        }
        bw.write(answer % 1000000000 + "");

        br.close();
        bw.close();
    }

}
