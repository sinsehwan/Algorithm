
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());

        long a = 0;
        long b = 1;

        long temp;

        for(int i = 2; i <= n; i++){
            temp = a;
            a += b;
            b = temp;
        }

        System.out.println(a + b + "");

        br.close();
        bw.close();
    }
}
