
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] numList = new int[10];
        int[] nextNumList = new int[10];

        for(int i = 0; i < 10; i++){
            numList[i] = 1;
        }

        int sum = 0;

        for(int i = 1; i < n; i++){
            sum = 0;
            for(int j = 9; j >= 0; j--){
                sum = (numList[j] + sum) % 10007;
                nextNumList[j]  = sum;
            }
            for(int j = 0; j < 10; j++){
                numList[j] = nextNumList[j];
            }
        }

        int answer = 0;
        for(int item : numList){
            answer += item;
        }
        bw.write(answer % 10007 + "");

        br.close();
        bw.close();
    }

}
