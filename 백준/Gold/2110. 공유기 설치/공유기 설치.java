import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, c;

    static ArrayList<Integer> places = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            places.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(places);

        bw.write(getUpperIdx(1, Integer.MAX_VALUE) + "");

        br.close();
        bw.close();
    }


    public static int getUpperIdx(int st, int en){
        while(st < en){
            int mid = st + (en - st + 1) / 2;
            //System.out.println("st : " + st + ", en : " + en);
            if(cLeft(mid)){
                en = mid - 1;
            }
            else{
                st = mid;
            }
        }
        return st;
    }

    public static boolean cLeft(int len){
        int left = c;

        int pre = places.get(0);
        left -= 1;
        for(int i = 0; i < n; i++){
            if(places.get(i) - pre >= len){
                pre = places.get(i);
                left -= 1;
            }
        }

        if(left > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
