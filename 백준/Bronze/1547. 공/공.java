import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean[] cup = new boolean[4];

    public static void main(String[] args) throws IOException {
        cup[1] = true;

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            swap(a, b);
        }

        for(int i = 1; i <= 3; i++){
            if(cup[i]){
                bw.write(i + "");
            }
        }


        br.close();
        bw.close();
    }

    public static void swap(int idx1, int idx2){
        boolean temp = cup[idx1];
        cup[idx1] = cup[idx2];
        cup[idx2] = temp;
    }
}
