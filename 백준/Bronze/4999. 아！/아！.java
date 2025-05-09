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
        String s1 = br.readLine();
        String s2 = br.readLine();

        if(s1.length() < s2.length()){
            bw.write("no");
        }
        else{
            bw.write("go");
        }
    }
}
