import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();

        dp = new int[n][3];

        dp[0][1] = arr[0];
        if(n > 1){
            dp[1][2] = dp[0][1] + arr[1];
            dp[1][1] = arr[1];
        }

        for(int i = 2; i < n; i++){
            dp[i][2] = dp[i - 1][1] + arr[i];
            dp[i][1] = Math.max(dp[i - 2][1] + arr[i], dp[i - 2][2] + arr[i]);
        }

        int best;
        if(dp[n - 1][2] > dp[n - 1][1]){
            best = dp[n - 1][2];
        }
        else{
            best = dp[n - 1][1];
        }

        bw.write(best + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
    }


}
