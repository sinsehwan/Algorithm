import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, d, k, c;
    int[] cho;
    int[] choCount;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int count = init();
        int best = count;

        for(int i = 0; i < n; i++){
            choCount[cho[i]] += 1;
            if(choCount[cho[i]] == 1){
                count += 1;
            }

            choCount[cho[(n - k + i) % n]] -= 1;
            if(choCount[cho[(n - k + i) % n]] == 0){
                count -= 1;
            }

            best = Math.max(count, best);
        }

        bw.write(best + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cho = new int[n];
        choCount = new int[d + 1];

        for(int i = 0; i < n; i++){
            cho[i] = Integer.parseInt(br.readLine());
        }
    }

    int init(){
        // c는 깍두기. 0이 될 수 없다.
        choCount[c] += 1;
        for(int i = n - k; i < n; i++){
            choCount[cho[i]] += 1;
        }

        int count = 0;
        for(int i = 1; i <= d; i++){
            if(choCount[i] > 0){
                count += 1;
            }
        }
        return count;
    }
}
