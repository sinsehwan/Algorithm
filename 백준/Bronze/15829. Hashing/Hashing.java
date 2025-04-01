import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        int n = Integer.parseInt(br.readLine());

        String str = br.readLine();

        long hash = 0;

        for(int i = 0; i < str.length(); i++){
            hash = getHash(hash + getPower(str.charAt(i) - 'a' + 1, i));
        }
        bw.write(hash + "");
    }

    long getPower(long value, int po){
        long result = value;
        for(int i = 0; i < po; i++){
            result = getHash(31 * result);
        }
        return result;
    }

    long getHash(long num){
        return num % 1234567891;
    }
}
