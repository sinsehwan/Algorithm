import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] value;
    int[] count;
    boolean[] dp;
    int total = 0;

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 3; i++) {
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        if((total & 1) == 1) {
            bw.write("0\n");
            return;
        }

        dp[0] = true;

        for(int i = 0; i < n; i++){
            for(int j = total / 2; j >= value[i]; j--){
                if(dp[j - value[i]]){
                    for(int k = 0; k < count[i]; k++){
                        if(j + value[i] * k > total / 2) break;
                        dp[j + value[i] * k] = true;
                    }
                }
            }
        }

        if(dp[total / 2]){
            bw.write("1\n");
        }
        else{
            bw.write("0\n");
        }
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        value = new int[n];
        count = new int[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            value[i] = Integer.parseInt(st.nextToken());
            count[i] = Integer.parseInt(st.nextToken());

            total += value[i] * count[i];
        }
        dp = new boolean[total / 2 + 1];
    }
}
