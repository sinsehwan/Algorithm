import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int[] cost;

    int maxCost = 0;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();


        bw.write(getLowerBound() + "");
    }

    int getLowerBound() {
        int st = maxCost;
        int en = Integer.MAX_VALUE;

        while(st < en) {
            int mid = st + (en - st) / 2;

            if (m < getCount(mid)) {
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }

        return st;
    }

    int getCount(int price) {
        int account = price;
        int count = 1;

        for(int c : cost) {
            if(account < c) {
                account = price;
                count += 1;
            }
            account -= c;
        }

        return count;
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cost = new int[n];
        for(int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(br.readLine());

            maxCost = Math.max(cost[i], maxCost);
        }
    }
}
