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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();

        for(int i = 0; i < n; i++){
            set.add(br.readLine());
        }

        for(int i = 0; i < m; i++){
            String[] str = br.readLine().split(",");
            for(String item : str){
                set.remove(item);
            }
            bw.write(set.size() + "\n");
        }
    }
}
