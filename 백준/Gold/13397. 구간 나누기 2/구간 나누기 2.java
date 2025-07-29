import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int[] arr;

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
        int st = 0;
        int en = 10000;

        while(st < en) {
            int mid = st + (en - st) / 2;

            if(m < getSectionCount(mid)) {
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }

        return st;
    }

    int getSectionCount(int sectionMaxScore) {
        int count = 1;
        int sectionMax = 0;
        int sectionMin = Integer.MAX_VALUE;
        for(int num : arr) {
            if (num > sectionMax) {
                sectionMax = num;
            }
            if (num < sectionMin) {
                sectionMin = num;
            }

            if (sectionMax - sectionMin > sectionMaxScore) {
                count += 1;
                sectionMin = num;
                sectionMax = num;
            }
        }

        return count;
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
