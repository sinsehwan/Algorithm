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
        int[] arr;
        while(true){
            arr = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();

            if(arr.length == 1 && arr[0] == 0){
                break;
            }

            bw.write(isPalindrome(arr) ? "yes" : "no");
            bw.write("\n");
        }
    }

    boolean isPalindrome(int[] arr){
        int idx1 = 0;
        int idx2 = arr.length - 1;

        while(idx1 < idx2){
            if(arr[idx1] != arr[idx2]){
                return false;
            }
            idx1 += 1;
            idx2 -= 1;
        }

        return true;
    }
}
