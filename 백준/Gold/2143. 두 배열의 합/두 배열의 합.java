import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static long t;

    static int n, m;
    static int[] a, b;

    static long[] sumA, sumB;

    static ArrayList<Long> sectionA = new ArrayList<>();
    static ArrayList<Long> sectionB = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        t = Long.parseLong(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
        m = Integer.parseInt(br.readLine());
        b = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
        sumA = new long[n];
        sumB = new long[m];

        sumA[0] = a[0];
        sumB[0] = b[0];
        for(int i = 1; i < n; i++){
            sumA[i] = a[i];
            sumA[i] += sumA[i - 1];
        }

        for(int i = 1; i < m; i++){
            sumB[i] = b[i];
            sumB[i] += sumB[i - 1];
        }

        // get sectionSum
        for(int i = 0; i < n; i++){
            sectionA.add(sumA[i]);
            for(int j = i + 1; j < n; j++){
                sectionA.add(sumA[j] - sumA[i]);
            }
        }
        for(int i = 0; i < m; i++){
            sectionB.add(sumB[i]);
            for(int j = i + 1; j < m; j++){
                sectionB.add(sumB[j] - sumB[i]);
            }
        }
        //Collections.sort(sectionA);
        Collections.sort(sectionB);
        /*System.out.println("sectionB");
        for (long item: sectionB){
            System.out.print(item + " ");
        }
        System.out.println();
        
         */

        long total = 0;

        for(long item : sectionA){
            //System.out.println("item : " + item);
            int upperIdx = getUpperIdx(item);
            int lowerIdx = getLowerIdx(item);
            /*if(upperIdx != lowerIdx){
                System.out.println("up : " + upperIdx + ", lo : " + lowerIdx);
                System.out.println("sectionA : " + item);
            }
            */
            total += upperIdx - lowerIdx;
        }

        bw.write(total + "");


        br.close();
        bw.close();
    }

    public static int getUpperIdx(long sectionSumA){
        int st = 0;
        int en = sectionB.size();

        while(st < en){
            int mid = st + (en - st) / 2;

            if(sectionB.get(mid) + sectionSumA <= t){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

    public static int getLowerIdx(long sectionSumA){
        int st = 0;
        int en = sectionB.size();

        while(st < en){
            int mid = st + (en - st) / 2;

            if(sectionB.get(mid) + sectionSumA < t){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }
}
