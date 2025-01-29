import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        long n = Long.parseLong(br.readLine());

        bw.write(getUpperIdx(n) + "");

        br.close();
        bw.close();
    }


    public static long getUpperIdx(long target){
        long st = 0;
        long en = Long.MAX_VALUE;

        while(st < en){
            //System.out.println("st : " + st + ", en : " + en);
            long mid = st + (en - st) / 2;

            if(Math.pow(mid, 2) >= target){
                en = mid;
            }
            else{
                st = mid + 1;
            }
        }

        return st;
    }
}
