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

    void solve() throws IOException{
        int t = Integer.parseInt(br.readLine());
        int a = t / 300;
        t %= 300;
        int b = t / 60;
        t %= 60;
        int c = t / 10;
        t %= 10;
        if(t > 0){
            bw.write("-1");
        }
        else{
            bw.write(a + " " + b + " " + c);
        }
    }
}
