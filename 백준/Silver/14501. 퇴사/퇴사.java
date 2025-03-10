import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    //work[i][0] : i번째 일을 수행하면 끝나는 날짜
    static int[][] work;
    //dp[i] : i번째 날짜까지 일 했을 때 가능한 Max값
    static int[] dp;

    public static void main(String[] args) throws IOException {
        getInput();

        dp = new int[n + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                if(work[j][0] <= i){
                    dp[i] = Math.max(dp[i], dp[j - 1] + work[j][1]);
                }
            }
        }

        bw.write(dp[n] + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        work = new int[n + 1][2];

        StringTokenizer st;
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken()) + i - 1;
            work[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
