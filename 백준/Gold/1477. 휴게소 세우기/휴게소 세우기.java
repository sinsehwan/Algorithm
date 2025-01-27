import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static ArrayList<Integer> areas = new ArrayList<>();

    static int n, m, l;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            areas.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(areas);

        bw.write(getLowerIdx(1, Integer.MAX_VALUE) + "");

        br.close();
        bw.close();
    }

    public static int getLowerIdx(int st, int en){
        while(st < en){
            //System.out.println("st : " + st + ", en : " + en);
            int mid = st + (en - st) / 2;

            if(needMorePlace(mid)){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

    public static boolean needMorePlace(int len){
        int total = 0;
        int pre = 0;

        for(int item : areas){
            if((item - pre) % len == 0){
                total += (item - pre) / len - 1;
                //System.out.println("item : " + item);
                //System.out.println((item - pre) / len - 1);
            }
            else{
                total += (item - pre) / len;
                //System.out.println("item : " + item);
                //System.out.println((item - pre) / len);
            }
            pre = item;
        }

        int last = l - pre;
        if(last % len == 0){
            total += last / len - 1;
        }
        else{
            total += last / len;
        }
        //System.out.println(total);
        return total > m;
    }


}
