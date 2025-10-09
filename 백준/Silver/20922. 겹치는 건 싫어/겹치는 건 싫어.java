import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, k;
    int[] arr;
    int[] count;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int lIdx = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            count[arr[i]] += 1;

            while (count[arr[i]] > k) {
                count[arr[lIdx]] -= 1;
                lIdx += 1;
            }

            answer = Math.max(i - lIdx + 1, answer);
        }

        bw.write(answer + "");
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        count = new int[100001];
    }
}
