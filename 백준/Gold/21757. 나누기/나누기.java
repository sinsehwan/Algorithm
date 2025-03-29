import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n;
    long[] arr;
    int[] mode;
    long[] dp;


    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();
        mode = new int[n];
        dp = new long[n];

        for(int i = 1; i < n; i++){
            arr[i] += arr[i - 1];
        }

        if(arr[n - 1] % 4 != 0){
            bw.write("0");
            return;
        }

        bw.write(calDp() + "");
    }

    long calDp(){
        long s1 = arr[n - 1] / 4;
        long s2 = arr[n - 1] / 2;
        long s3 = arr[n - 1] / 4 * 3;
        long s4 = arr[n - 1];

        for(int i = 0; i < n; i++){
            if(arr[i] == s1){
                mode[i] = 1;
                dp[i] = 1;
            }
            else if(arr[i] == s2){
                mode[i] = 2;
            }
            else if(arr[i] == s3){
                mode[i] = 3;
            }
            else if(arr[i] == s4){
                mode[i] = 4;
            }
        }


        for(int k = 1; k < 4; k++){
            long count = 0;
            for(int i = 0; i < n; i++){
                if(mode[i] == k){
                    count += dp[i];
                }
                else if(mode[i] == k + 1){
                    dp[i] = count;
                }
            }
        }
        long answer = 0;
        for(int i = 0; i < n; i++){
            if(mode[i] == 4){
                answer += dp[i];
            }
        }
        return answer;
    }
}
