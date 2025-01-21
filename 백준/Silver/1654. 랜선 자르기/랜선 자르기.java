
import java.io.*;
import java.util.*;

public class Main {
    static int k, n;
    static int[] lanList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        lanList = new int[k];
        for (int i = 0; i < k; i++) {
            lanList[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(binarySearch(0, (long) Integer.MAX_VALUE));
        br.close();
    }

    public static int makeLan(int[] lanList, int length, int n) {
        int count = 0;
        int minLeft = Integer.MAX_VALUE;

        for (int lan : lanList) {
            count += lan / length;
            if (minLeft > lan % length) {
                minLeft = lan % length;
            }
        }

        if (count < n) {
            return -1;  // 랜선 부족
        } else if (count == n && minLeft == 0) {
            return 0;   // 정확한 개수로 나누어 떨어짐
        } else {
            return 1;   // 여분이 있음
        }
    }

    public static long binarySearch(long start, long end) {
        long mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            int result = makeLan(lanList, (int) mid, n);

            if (result == -1) {
                end = mid - 1;
            } else if (result == 0) {
                return mid;
            } else {  // result == 1
                start = mid + 1;
            }
        }
        return end;
    }
}

