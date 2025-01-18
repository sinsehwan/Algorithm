
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, k;

    static int[][] dp;

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[k + 1][n + 1];

        for(int i = 0; i <= n; i++){
            dp[1][i] = 1;
            dp[0][i] = 1;
        }

        int result = findCaseNum(k, n);

        bw.write(result + "");

        br.close();
        bw.close();
    }

    public static int findCaseNum(int numCase, int target){
        if(dp[numCase][target] != 0){
            return dp[numCase][target];
        }

        int answer = 0;

        for(int i = 0; i <= target; i++){
            answer = (answer + findCaseNum(numCase - 1, i)) % 1000000000;
        }

        dp[numCase][target] = answer;
        //System.out.println("dp[" + numCase + "][" + target + "] : " + answer);
        return answer;
    }
}
