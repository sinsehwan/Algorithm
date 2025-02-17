import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int total = 0;

        int[] arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        for(int item : arr){
            total += item * item;
        }

        bw.write(total % 10 + "");

        br.close();
        bw.close();
    }
}
