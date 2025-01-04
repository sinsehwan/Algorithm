
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] arr;

    static int[] sum;
    static int[] minSum;
    static int minIndex = 0;

    static int bestSum;
    static int n;
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        sum = new int[n];
        minSum = new int[n];
        sum[0] = arr[0];
        bestSum = sum[0];
        for(int i = 1; i < n; i++){
            sum[i] = sum[i - 1] + arr[i];
            if(sum[i] > bestSum){
                bestSum = sum[i];
            }
        }

        minSum[0] = sum[0];
        for(int i = 1; i < n; i++){
            if(sum[minIndex] > 0){
                minSum[i] = 0;
            }
            else{
                minSum[i] = sum[minIndex];
            }
            if(sum[i - 1] < minSum[i]){
                minSum[i] = sum[i - 1];
                minIndex = i - 1;
            }
        }

        for(int i = 1; i < n; i++){
            if(sum[i] - minSum[i] > bestSum){
                bestSum = sum[i] - minSum[i];
            }
        }
        System.out.println(bestSum);


        br.close();
        bw.close();
    }
}
