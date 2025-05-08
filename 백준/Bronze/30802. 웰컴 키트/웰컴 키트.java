import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] li;
    int t, p;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int tNum = 0;
        for(int item : li){
            tNum += item / t;
            item %= t;
            if(item != 0){
                tNum += 1;
            }
        }
        bw.write(tNum + "\n");
        int pNum = n / p;
        int pLast = n % p;
        bw.write(pNum + " " + pLast);
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        li = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
    }
}
