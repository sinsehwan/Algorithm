import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] arr;
    static int best1 = Integer.MAX_VALUE, best2 = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        findBest();

        if(best2 > best1){
            bw.write(best1 + " " + best2);
        }
        else{
            bw.write(best2 + " " + best1);
        }

        br.close();
        bw.close();
    }

    public static void findBest() throws IOException {
        int best = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int idx = binarySearch(arr[i]);
            //bw.write(arr[i] + " " + arr[idx] + "\n");
            if(Math.abs(arr[i] + arr[idx]) < best && i != idx){
                best = Math.abs(arr[i] + arr[idx]);
                best1 = arr[i];
                best2 = arr[idx];
            }
        }
    }

    public static int binarySearch(int value){
        int st = 0;
        int en = n - 1;
        int best = Integer.MAX_VALUE;
        int bestIdx = -1;
        while(st <= en){
            int mid = st + (en - st) / 2;

            if(arr[mid] + value < 0){
                st = mid + 1;
                if(Math.abs(arr[mid] + value) < best){
                    best = Math.abs(arr[mid] + value);
                    bestIdx = mid;
                }
            }
            else if(arr[mid] + value > 0){
                en = mid - 1;
                if(Math.abs(arr[mid] + value) < best){
                    best = Math.abs(arr[mid] + value);
                    bestIdx = mid;
                }
            }
            else{
                return mid;
            }
        }
        return bestIdx;
    }
}
