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
        Set<Character> st = new HashSet<>();
        String str = br.readLine();
        String str2 = br.readLine();
        for(char c : str.toCharArray()){
            st.add(c);
        }

        for(char c : str2.toCharArray()){
            if(!st.contains(c)){
                bw.write(c + "");
            }
        }
    }
}
