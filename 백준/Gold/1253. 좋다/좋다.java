import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static boolean[] good;

    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        good = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int target = arr.get(i) + arr.get(j);
                int idx = binarySearch(target);
                if(idx != -1 && idx != i && idx != j){
                    good[idx] = true;
                }
            }
        }

        int goodNum = 0;

        for(int i = 0; i < n; i++){
            if(good[i]){
                goodNum += getUpperIdx(arr.get(i)) - getLowerIdx(arr.get(i));
                //System.out.println(getUpperIdx(arr.get(i)) - getLowerIdx(arr.get(i)) + ", " + arr.get(i));
            }
        }

        bw.write(goodNum + "");

        br.close();
        bw.close();
    }

    public static int binarySearch(int target){
        int st = 0;
        int en = n - 1;

        while(st <= en){
            int mid = st + (en - st) / 2;
            if(arr.get(mid) < target){
                st = mid + 1;
            }
            else if(arr.get(mid) > target){
                en = mid - 1;
            }
            else{
                return mid;
            }
        }
        return -1;
    }

    public static int getUpperIdx(int target){
        int st = 0;
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;

            if(arr.get(mid) <= target){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

    public static int getLowerIdx(int target){
        int st = 0;
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;

            if(arr.get(mid) < target){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

}
