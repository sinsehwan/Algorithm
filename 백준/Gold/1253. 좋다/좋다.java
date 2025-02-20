import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static int[] arr;

    static boolean[] isGood;

    public static void main(String[] args) throws IOException {
        init();

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int sum = arr[i] + arr[j];

                int idx = binarySearch(sum);
                if(idx != -1 && idx != i && idx != j){
                    isGood[idx] = true;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            if(isGood[i]){
                count += getUpperIdx(arr[i]) - getLowerIdx(arr[i]);
            }
        }

        bw.write(count + "");

        br.close();
        bw.close();
    }

    public static void init() throws IOException{
        n = Integer.parseInt(br.readLine());
        isGood = new boolean[n];
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);
    }

    public static int binarySearch(int target){
        int st = 0;
        int en = n - 1;

        while(st <= en){
            int mid = st + (en - st) / 2;
            if(arr[mid] < target){
                st = mid + 1;
            }
            else if(arr[mid] > target){
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

            if(arr[mid] <= target){
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

            if(arr[mid] < target){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }
}
