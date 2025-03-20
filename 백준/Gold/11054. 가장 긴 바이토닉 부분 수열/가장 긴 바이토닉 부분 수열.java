import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        new Main().solve();


        br.close();
        bw.close();
    }

    void solve() throws IOException{
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        //dp[][][0]은 현재 증가하는 경우 최대길이
        //dp[][][1]은 현재 감소하는 경우 최대길이
        int[][][] dp = new int[n][n][2];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = i; k < j; k++){
                    if(arr[j] > arr[k]){
                        dp[i][j][0] = Math.max(dp[i][k][0], dp[i][j][0]);
                    }
                    if(arr[j] < arr[k]){
                        // 기존 감소중이던 배열에 덧붙이는 경우
                        dp[i][j][1] = Math.max(dp[i][k][1], dp[i][j][1]);
                        // 현재를 변곡점으로 설정하는 경우
                        dp[i][j][1] = Math.max(dp[i][k][0], dp[i][j][1]);
                    }
                }

                dp[i][j][1] += 1;
                dp[i][j][0] += 1;

            }
        }

        int answer = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int best = Math.max(dp[i][j][0], dp[i][j][1]);
                answer = Math.max(best, answer);
                //bw.write(i + " " + j + " " + dp[i][j][0] + " " + dp[i][j][1] + "\n");
            }
        }
        bw.write(answer + "");
    }
}
