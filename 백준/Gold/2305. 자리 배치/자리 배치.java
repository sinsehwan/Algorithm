import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, k;
    // n개의 지정석에 앉는 경우의 수
    int[] seatCase;
    // seatCase2[n][k] : n개 지정석 중, k번째 사람이 부재 시 경우의 수
    int[][] seatCase2;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
        init();

        int total = 0;
        //자유석에 아무도 안 앉는 경우
        total += seatCase[k - 1] * seatCase[n - k];
        //i가 자유석에 앉는 경우
        for(int i = 1; i <= n; i++){
            if(i == k) continue;
            if(i < k){
                total += calCase2(k - 1, i) * seatCase[n - k];
            }
            else{
                total += seatCase[k - 1] * calCase2(n - k, i - k);
            }
        }

        bw.write(total + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        seatCase = new int[n + 1];
        seatCase2 = new int[n + 1][n + 1];
    }

    void init(){
        seatCase[0] = 1;
        seatCase[1] = 1;

        for(int i = 2; i <= n; i++){
            seatCase[i] = seatCase[i - 2] + seatCase[i - 1];
        }
        seatCase2[1][1] = 1;
        //seatCase2[2][1] = 2;
        //seatCase2[2][2] = 2;
    }

    int calCase2(int n, int k){
        if(seatCase2[n][k] > 0) return seatCase2[n][k];
        //System.out.println(n + " " + k);

        int result = 0;
        // k에 아무도 안 앉는 경우
        result += seatCase[k - 1] * seatCase[n - k];
        // k - 1이 k에 앉는 경우
        if(n > 1 && k > 1){
            result += calCase2(k - 1, k - 1) * seatCase[n - k];
        }
        // k + 1이 k에 앉는 경우
        if(n > k){
            result += seatCase[k - 1] * calCase2(n - k, 1);
        }

        seatCase2[n][k] = result;
        return result;
    }
}
