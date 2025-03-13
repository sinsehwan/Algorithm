import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, x;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        getInput();

        slidingWindow();

        br.close();
        bw.close();
    }

    static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }

    static void slidingWindow() throws IOException{
        int total = 0;

        for(int i = 0; i < x; i++){
            total += arr[i];
        }
        int best = total;
        int bestCount = 1;

        for(int i = x; i < n; i++){
            total -= arr[i - x];
            total += arr[i];
            if(total > best){
                best = total;
                bestCount = 1;
            }
            else if(total == best){
                bestCount += 1;
            }
        }

        if(best == 0){
            bw.write("SAD");
        }
        else{
            bw.write(best + "\n");
            bw.write(bestCount + "");
        }
    }
}
