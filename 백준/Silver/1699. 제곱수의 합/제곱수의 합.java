
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] minNum;

    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());

        minNum = new int[n + 1];

        for(int i = 1; i <= n; i++){
            minNum[i] = 100000;
        }

        minNum[1] = 1;

        for(int i = 2; i <= n; i++){
            for(int j = 1; j * j <= i; j++){
                if(minNum[i] > 1 + minNum[i - j * j]){
                    minNum[i] = 1 + minNum[i - j*j];
                }
            }
        }

        /*for(int item : minNum){
            bw.write(item + "");
        }
        System.out.println();
        */
        bw.write(minNum[n] + "");


        br.close();
        bw.close();
    }
}
