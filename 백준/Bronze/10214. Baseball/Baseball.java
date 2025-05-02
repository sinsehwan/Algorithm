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
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < t; i++){
            int a = 0, b = 0;

            for(int j = 0; j < 9; j++){
                st = new StringTokenizer(br.readLine());

                a += Integer.parseInt(st.nextToken());
                b += Integer.parseInt(st.nextToken());
            }
            if(a > b){
                bw.write("Yonsei\n");
            }
            else if(a < b){
                bw.write("Korea\n");
            }
            else{
                bw.write("Draw\n");
            }
        }
    }

}
