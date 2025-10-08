import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String str;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int tIdx = 0;

        for (int i = 1; i <= 30000; i++) {
            String temp = Integer.toString(i);

            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == str.charAt(tIdx)) {
                    tIdx += 1;
                }

                if (tIdx == str.length()) {
                    bw.write(i + " ");
                    return;
                }
            }
        }
    }

    void getInput() throws IOException {
        str = br.readLine();
    }
}
