import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int turn = Integer.parseInt(st.nextToken());

        int moveCount = 0;

        int[] arr = new int[20];
        for(int i = 0; i < 20; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sorted = new int[20];
        for(int i = 0; i < 20; i++){
            sorted[i] = arr[i];
            for(int j = 0; j < i; j++){
                if(sorted[j] > arr[i]){
                    int temp = arr[i];
                    for(int k = i; k > j; k--){
                        sorted[k] = sorted[k - 1];
                        moveCount += 1;
                    }
                    sorted[j] = temp;
                    break;
                }
            }
        }

        bw.write(turn + " " + moveCount + "\n");
    }
}
