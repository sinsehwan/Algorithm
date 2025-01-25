import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static ArrayList<Integer> arr = new ArrayList<>();
    static ArrayList<Integer> two = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                two.add(arr.get(i) + arr.get(j));
            }
        }

        Collections.sort(two);

        int answer = -1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int result = binarySearch(arr.get(i) - arr.get(j));

                if(result != -1){
                    if(arr.get(i) > answer){
                        answer = result + arr.get(j);
                    }
                }
            }
        }

        bw.write(answer + "");
        br.close();
        bw.close();
    }

    public static int binarySearch(int target){
        if(target < 0) return -1;
        int st = 0;
        int en = two.size() - 1;

        while(st <= en){
            int mid = st + (en - st) / 2;

            if(two.get(mid) < target){
                st = mid + 1;
            }
            else if(two.get(mid) > target){
                en = mid - 1;
            }
            else{
                return two.get(mid);
            }
        }
        return -1;
    }
}
