
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int caseNum = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        Set<Integer> set = new LinkedHashSet<>();

        for (int item : arr){
            set.add(item);
        }

        for(int item : set){
            bw.write(item + " ");
        }

        br.close();
        bw.close();
    }
}
