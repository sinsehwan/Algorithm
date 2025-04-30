import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] switches;
    int m;
    int[][] order;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < m; i++){
            reflect(order[i][0], order[i][1]);
        }

        for(int i = 1; i <= n; i++){
            bw.write(switches[i] + " ");
            if(i % 20 == 0){
                bw.write("\n");
            }
        }
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        switches = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            switches[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        order = new int[m][2];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            order[i][0] = Integer.parseInt(st.nextToken());
            order[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    void reflect(int mode, int target){
        if(mode == 1){
            for(int i = 1; i <= n; i++){
                if(i % target == 0){
                    switches[i] = (switches[i] + 1) % 2;
                }
            }
        }
        else {
            int pre = 1;
            switches[target] = (switches[target] + 1) % 2;
            while(target - pre >= 1 && target + pre <= n){
                if(switches[target - pre] != switches[target + pre]){
                    break;
                }
                switches[target - pre] = (switches[target - pre] + 1) % 2;
                switches[target + pre] = (switches[target + pre] + 1) % 2;
                pre += 1;
            }
        }
    }
}
