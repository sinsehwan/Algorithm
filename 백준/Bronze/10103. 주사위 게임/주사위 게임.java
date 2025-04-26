import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());

        int s1 = 100;
        int s2 = 100;

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a != b){
                if(a > b){
                    s2 -= a;
                }
                else{
                    s1 -= b;
                }
            }
        }
        bw.write(s1 + "\n" + s2);
    }
}
