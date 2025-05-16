import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, t;
    int[] arr;
    int[] arr2;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int i = 0; i < t; i++){
            arr2[i] = arr[i];
        }

        Arrays.sort(arr2);

        for(int item : arr2){
            bw.write(item + " ");
        }
        for(int i = t; i < n; i++){
            bw.write(arr[i] + " ");
        }
    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        arr2 = new int[t];
    }
}
