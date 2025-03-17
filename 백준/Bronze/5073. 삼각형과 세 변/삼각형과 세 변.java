import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        new Main().solve();
    }

    void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0){
                break;
            }

            if(a == b && b == c){
                bw.write("Equilateral\n");
            }
            else{
                int best = Math.max(a, Math.max(b, c));
                int min = Math.min(a, Math.min(b, c));
                int mid = a + b + c - best - min;

                if(best >= min + mid){
                    bw.write("Invalid\n");
                }
                else if(a == b || b == c || a == c){
                    bw.write("Isosceles\n");
                }
                else{
                    bw.write("Scalene\n");
                }
            }
        }

        br.close();
        bw.close();
    }
}
