
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int[] arr = new int[7];

        for(int i = 0; i < 7; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0;
        int min = -1;

        for(int item : arr){
            if(item % 2 == 1){
                sum += item;

                if(item < min || min == -1){
                    min = item;
                }
            }
        }


        if(sum == 0){
            bw.write("-1");
        }
        else{
            bw.write(sum + "\n");
            bw.write(min + "");
        }

        br.close();
        bw.close();
    }

}
