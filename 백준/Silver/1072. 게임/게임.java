
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static long x, y, z;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Long.parseLong(st.nextToken());
        y = Long.parseLong(st.nextToken());
        z = y * 100 / x;
        long answer = -1;

        if(z < 99){
            answer = getLowerIdx(1, Long.MAX_VALUE / 100);
        }

        bw.write(answer + "");


        br.close();
        bw.close();
    }

    public static long getLowerIdx(long st, long en){
        while(st < en){
            //System.out.println("st : " + st + ", en : " + en);
            long mid = st + (en - st) / 2;

            if(!isSufficient(mid)){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

    public static boolean isSufficient(long value){
        //System.out.println((y + value) * 100 / (x + value));
        //System.out.println(z);
        return (y + value) * 100 / (x + value) - z >= 1;
    }

}
