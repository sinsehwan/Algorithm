import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static int[] tk;

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tk = new int[n];
        for(int i = 0; i < n; i++){
            tk[i] = Integer.parseInt(br.readLine());
        }

        bw.write(getLowerTime(0, Long.MAX_VALUE) + "");
        br.close();
        bw.close();
    }



    public static long getLowerTime(long st, long en){
        while(st < en){
            //System.out.println("st : " + st + ", en : " + en);
            long mid = st + (en - st) / 2;

            if(isSufficient(mid)){
                en = mid;
            }
            else{
                st = mid + 1;
            }
        }
        return st;
    }

    public static boolean isSufficient(long time){
        long total = 0;

        for(int item : tk){
            total += time / item;
            if(total >= m) break;
        }

        return total >= m;
    }
}
