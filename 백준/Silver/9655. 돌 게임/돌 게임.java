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
        if(n % 2 == 1){
            bw.write("SK");
        }
        else{
            bw.write("CY");
        }
    }
}
