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
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            double a = Double.parseDouble(st.nextToken());
            while(st.hasMoreTokens()){
                char op = st.nextToken().charAt(0);
                if(op == '@'){
                    a *= 3;
                }
                else if(op == '%'){
                    a += 5;
                }
                else if(op == '#'){
                    a -= 7;
                }
            }

            System.out.printf("%.2f\n", a);
        }
    }
}
