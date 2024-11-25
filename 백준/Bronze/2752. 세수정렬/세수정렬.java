
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        for(int item : arr){
            bw.write(item + " ");
        }
        br.close();
        bw.close();
    }
}
