import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    long[] arr;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        long bestSum = Long.MAX_VALUE;
        int[] best3 = new int[3];
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int k = thirdSearch(i, j);
                if(k == -1) continue;

                long sum = Math.abs(arr[i] + arr[j] + arr[k]);
                if(sum < bestSum){
                    bestSum = sum;
                    best3[0] = i;
                    best3[1] = j;
                    best3[2] = k;
                }
            }
        }

        for(int i : best3){
            bw.write(arr[i] + " ");
        }
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();

        Arrays.sort(arr);
    }

    // i1 < i2 < i3
    int thirdSearch(int i1, int i2){
        long twoSum = arr[i1] + arr[i2];

        int st = Math.max(i1, i2) + 1;
        if(st == n) return -1;
        int lIdx = getLowerIdx(-twoSum, st);

        // lIdx가 시작점이거나 배열의 끝 다음일 경우 처리
        if(lIdx == n) return lIdx - 1;
        else if(lIdx == st) return lIdx;
        // st < lIdx < n인 경우
        // lIdx, lIdx - 1 둘 중 best 반환
        else{
            if(Math.abs(twoSum + arr[lIdx - 1]) < Math.abs(twoSum + arr[lIdx])){
                return lIdx - 1;
            }
            else{
                return lIdx;
            }
        }
    }

    int getLowerIdx(long value, int st){
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;

            if(value > arr[mid]){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }

        return st;
    }
}
