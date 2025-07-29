import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m, l;
    int[] positions;
    int[] order;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(int o : order) {
            bw.write(getUpperBound(o) - 1 + "\n");
        }
    }

    int getUpperBound(int target) {
        int st = 1;
        int en = Integer.MAX_VALUE;

        while(st < en) {
            int mid = st + (en - st) / 2;

            if (target <= getCutCount(mid)) {
                st = mid + 1;
            }
            else {
                en = mid;
            }
        }

        return st;
    }

    int getCutCount(int unit) {
        int present = 0;
        int count = 0;

        for(int i = 0; i < m; i++) {
            if(positions[i] - present >= unit && l - positions[i] >= unit) {
                present = positions[i];
                count += 1;
            }
        }

        return count;
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        positions = new int[m];
        for(int i = 0; i < m; i++) {
            positions[i] = Integer.parseInt(br.readLine());
        }

        order = new int[n];
        for(int i = 0; i < n; i++) {
            order[i] = Integer.parseInt(br.readLine());
        }
    }
}
