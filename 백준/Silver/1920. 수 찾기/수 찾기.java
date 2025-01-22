
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static ArrayList<Integer> arr = new ArrayList<>();

    static int[] checkList;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        m = Integer.parseInt(br.readLine());

        checkList = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        Collections.sort(arr);
        arr.add(0, 0);

        for(int item : checkList){
            bw.write(binary_search(1, n, item)+ "\n");
        }

        br.close();
        bw.close();
    }

    public static int binary_search(int start, int end, int target){

        while(start <= end){
            int mid = start + (end - start) / 2;
            //int mid = (start + end) / 2;
            //System.out.println(arr.get(mid) + ", " + target);
            if(arr.get(mid) < target){
                start = mid + 1;
            }
            else if(arr.get(mid) > target){
                end = mid - 1;
            }
            else{
                return 1;
            }
        }
        return 0;
    }

}
