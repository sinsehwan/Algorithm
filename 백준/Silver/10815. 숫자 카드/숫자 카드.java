import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static List<Integer> numList = new ArrayList<>();
    static int[] checkList;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            numList.add(Integer.parseInt(st.nextToken()));
        }

        m = Integer.parseInt(br.readLine());
        checkList = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        Collections.sort(numList);

        for(int item : checkList){
            bw.write(binary_search(0, numList.size() - 1, item) + " ");
        }

        br.close();
        bw.close();
    }

    public static int binary_search(int start, int end, int target){
        int answer = 0;

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(numList.get(mid) < target){
                start = mid + 1;
            }
            else if(numList.get(mid) > target){
                end = mid - 1;
            }
            else{
                answer = 1;
                return answer;
            }
        }
        return answer;
    }
}
