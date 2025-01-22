import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static int[] arr;

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        bw.write(binary_search(1, 1000000000) + "");

        br.close();
        bw.close();
    }

    public static int binary_search(int start, int end){
        int answer = 0;

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(getCount(mid) < n){
                end = mid - 1;
            }
            else{
                start = mid + 1;
                answer = mid;
            }
        }

        return answer;
    }

    public static int getCount(int size){
        int answer = 0;

        for(int item : arr){
            answer += item / size;
        }
        return answer;
    }
}
