import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, k;
    static int[] exam;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        exam = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        bw.write(getUpperScore() + "");

        br.close();
        bw.close();
    }

    public static int getUpperScore(){
        int st = 0;
        int en = Integer.MAX_VALUE - 1;

        while(st < en){
            //System.out.println("st : " + st + ", en : " + en);
            int mid = st + (en - st + 1) / 2;

            if(getGroupNum(mid) < k){
                en = mid - 1;
            }
            else{
                st = mid;
            }
        }

        return st;
    }

    public static int getGroupNum(int score){
        int sum = 0;
        int groupNum = 0;
        for(int item : exam){
            sum += item;
            if(sum >= score){
                groupNum += 1;
                sum = 0;
            }
        }

        return groupNum;
    }

}
