import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);

        long total = 0;

        for(int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                int preSum = arr.get(i) + arr.get(j);
                int minIdx = j + 1;

                int upperIdx = getUpperIdx(preSum, minIdx);
                int lowerIdx = getLowerIdx(preSum, minIdx);

                total += upperIdx - lowerIdx;
            }
        }
        bw.write(total + "");

        br.close();
        bw.close();
    }

    public static int getUpperIdx(int preSum, int minIdx){
        int st = minIdx;
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;

            if(arr.get(mid) + preSum <= 0){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

    public static int getLowerIdx(int preSum, int minIdx){
        int st = minIdx;
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;
            if(arr.get(mid) + preSum < 0){
                st = mid + 1;
            }
            else {
                en = mid;
            }
        }
        return st;
    }

}
