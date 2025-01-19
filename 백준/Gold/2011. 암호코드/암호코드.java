
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String inputString;

    static int n;
    static int arr[];
    static int[] dp;

    public static void main(String[] args) throws IOException{

        inputString = br.readLine();
        n = inputString.length();
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++){
            arr[i] = inputString.charAt(i - 1) - '0';
        }

        dp = new int[n + 1];

        dp[0] = 1;
        if(arr[1] != 0){
            dp[1] = 1;
        }

        for(int i = 2; i <= n; i++){
            boolean flag = false;

            if(arr[i] != 0){
                dp[i] += dp[i - 1];
                flag = true;
            }

            if(is2NumPossible(i)){
                dp[i] += dp[i - 2];
                flag = true;
            }

            dp[i] = dp[i] % 1000000;

            if(!flag){
                bw.write("0");
                br.close();
                bw.close();
                return;
            }

        }

        bw.write(dp[n] + "");

        br.close();
        bw.close();
    }

    public static boolean is2NumPossible(int i){
        if(arr[i - 1] == 1){
            return true;
        }
        else if(arr[i - 1] == 2 && arr[i] < 7){
            return true;
        }
        else{
            return false;
        }
    }

}
