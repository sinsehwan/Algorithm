import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n1, n2;
    static ArrayList<Integer> arr = new ArrayList<>();
    static ArrayList<Integer> check = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        for(int j = 0; j < t; j++){
            arr.clear();
            check.clear();
            n1 = Integer.parseInt(br.readLine());
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n1; i++){
                arr.add(Integer.parseInt(st.nextToken()));
            }

            n2 = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n2; i++){
                check.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(arr);

            for(int item : check){
                bw.write(binarySearch(item) + "\n");
            }
        }

        br.close();
        bw.close();
    }

    public static int binarySearch(int target){
        int st = 0;
        int en = n1 - 1;

        while(st <= en){
            int mid = st + (en - st) / 2;

            if(arr.get(mid) < target){
                st = mid + 1;
            }
            else if(arr.get(mid) > target){
                en = mid - 1;
            }
            else {
                return 1;
            }
        }

        return 0;
    }
}
