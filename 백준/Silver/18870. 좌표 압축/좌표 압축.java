import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static ArrayList<Integer> arr = new ArrayList<>();

    static ArrayList<Integer> sortedArr = new ArrayList<>();
    static ArrayList<Integer> newArr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int value = Integer.parseInt(st.nextToken());
            arr.add(value);
            sortedArr.add(value);
        }

        Collections.sort(sortedArr);

        for(int item : sortedArr){
            if(newArr.isEmpty() || newArr.get(newArr.size() - 1) != item){
                newArr.add(item);
                //System.out.println(item);
            }
        }

        for(int item : arr){
            bw.write(getUpperIdx(item) + " ");
        }



        br.close();
        bw.close();
    }

    public static int getUpperIdx(int target){
        int st = 0;
        int en = newArr.size() - 1;

        while(st <= en){
            //System.out.println("st : " + st + ", en : " + en);
            int mid = st + (en - st) / 2;

            if(newArr.get(mid) < target){
                st = mid + 1;
            }
            else if(newArr.get(mid) > target){
                en = mid - 1;
            }
            else{
                return mid;
            }
        }

        return -1;
    }

}
