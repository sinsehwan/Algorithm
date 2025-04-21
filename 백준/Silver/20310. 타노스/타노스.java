import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        int[] arr = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt).toArray();

        int n = arr.length;
        boolean[] removed = new boolean[n];

        int count0 = 0;
        int count1 = 0;
        int removed0 = 0;
        int removed1 = 0;

        for(int item : arr){
            if(item == 0){
                count0 += 1;
            }
            else{
                count1 += 1;
            }
        }

        int i = 0;
        while(removed1 < count1 / 2){

            if(arr[i] == 1){
                removed1 += 1;
                removed[i] = true;
            }
            i++;
        }

        i = n - 1;
        while(removed0 < count0 / 2){
            if(arr[i] == 0){
                removed0 += 1;
                removed[i] = true;
            }
            i--;
        }

        for(i = 0; i < n; i++){
            if(!removed[i]){
                bw.write(arr[i] + "");
            }
        }
    }
}
