import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static int[] a, b, c, d;

    //static ArrayList<Integer> twoSomeAB = new ArrayList<>();
    static int[] twoSomeCD;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];

        twoSomeCD = new int[n*n];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                //twoSomeAB.add(arr[i][0] + arr[j][1]);
                twoSomeCD[idx++] = c[i] + d[j];
            }
        }

        Arrays.sort(twoSomeCD);

        long total = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int item = a[i] + b[j];
                int upperIdx = getUpperIdx(item);
                int lowerIdx = getLowerIdx(item);

                total += upperIdx - lowerIdx;
            }
        }

        bw.write(total + "");

        br.close();
        bw.close();
    }

    public static int getUpperIdx(int preSum){
        int st = 0;
        int en = n*n;

        while(st < en){
            int mid = st + (en - st) / 2;
            if(twoSomeCD[mid] + preSum <= 0){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

    public static int getLowerIdx(int preSum){
        int st = 0;
        int en = n*n;

        while (st < en){
            int mid = st + (en - st) / 2;

            if(twoSomeCD[mid] + preSum < 0){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

}
