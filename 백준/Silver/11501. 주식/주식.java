import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int stockCount;
    int[] stock;
    int[] bestValue;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            new Main().solve();
            bw.write("\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        calBestValue();

        bw.write(calProfit() + "");
    }

    void calBestValue() {
        int best = 0;

        for (int i = stockCount - 1; i >= 0; i--) {
            if (stock[i] > best) {
                best = stock[i];
            }

            bestValue[i] = best;
        }
    }

    long calProfit() {
        long profit = 0;

        for (int i = 0; i < stockCount; i++) {
            profit += bestValue[i] - stock[i];
        }

        return profit;
    }

    void getInput() throws IOException {
        stockCount = Integer.parseInt(br.readLine());
        stock = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        bestValue = new int[stockCount];
    }
}
