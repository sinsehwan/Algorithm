import java.io.*;
import java.util.*;

public class Main {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[][] graph;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int minValue = Integer.MAX_VALUE;

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                for(int i = 1; i < n - 1; i++) {
                    for(int j = 1; j < n - 1; j++) {
                        if(isAllPromising(y, x, i, j)) {
                            minValue = Math.min(calculate(y, x, i, j), minValue);
                        }
                    }
                }
            }
        }
        bw.write(minValue + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for(int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    boolean isAllPromising(int y, int x, int d1, int d2) {
        return y >= 0 &&
                y + d1 + d2 < n &&
                0 <= x - d1 &&
                x + d2 < n;
    }

    int calculate(int y, int x, int d1, int d2) throws IOException {
        int[] arr = new int[6];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r < y + d1 && c <= x && r + c < y + x) {
                    arr[1] += graph[r][c];
                }
                else if(r <= y + d2 && x < c && r - c < y - x) {
                    arr[2] += graph[r][c];
                }
                else if(y + d1 <= r && c < x - d1 + d2 && r - c > y - x + 2 * d1) {
                    arr[3] += graph[r][c];
                }
                else if(y + d2 < r && x - d1 + d2 <= c && r + c > y + x + 2 * d2) {
                    arr[4] += graph[r][c];
                }
                else{
                    arr[5] += graph[r][c];
                }
            }
        }
        return calMaxMinDiffer(arr, 1);
    }

    int calMaxMinDiffer(int[] arr, int st) {
        int maxValue = 0;
        int minValue = Integer.MAX_VALUE;

        for(int i = st; i < arr.length; i++) {
            maxValue = Math.max(arr[i], maxValue);
            minValue = Math.min(arr[i], minValue);
        }

        return maxValue - minValue;
    }
}
