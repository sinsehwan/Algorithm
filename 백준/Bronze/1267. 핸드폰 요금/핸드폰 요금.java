import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        int total1 = 0, total2 = 0;

        for(int item : arr){
            total1 += (item / 30 + 1) * 10;
            total2 += (item / 60 + 1) * 15;
        }

        if(total1 < total2){
            bw.write("Y " + total1);
        }
        else if(total1 > total2){
            bw.write("M " + total2);
        }
        else{
            bw.write("Y M " + total1);
        }

        br.close();
        bw.close();
    }
}
