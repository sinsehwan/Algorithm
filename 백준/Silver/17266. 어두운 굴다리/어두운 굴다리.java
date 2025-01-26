import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static int[] place;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        place = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        bw.write(getLowerIdx() + "");

        br.close();
        bw.close();
    }

    public static int getLowerIdx(){
        int st = 0;
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;

            if(isSufficient(mid)){
                en = mid;
            }
            else{
                st = mid + 1;
            }
        }
        return st;
    }

    public static boolean isSufficient(int height){
        int pre = place[0];
        if(pre > height){
            return false;
        }

        int next = pre;
        for(int i = 1; i < m; i++){
            next = place[i];
            if(next - pre > 2 * height){
                return false;
            }
            pre = next;
        }

        if(n - next > height){
            return false;
        }
        return true;
    }

}
