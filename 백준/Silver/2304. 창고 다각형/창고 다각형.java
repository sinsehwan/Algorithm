import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int start = 100000;
    static int end = 0;

    public static void main(String[] args) throws IOException {
        new Main().solve();


        br.close();
        bw.close();
    }

    void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] height = new int[1001];

        int best = 0;
        int bestIdx = 0;
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            if(x < start){
                start = x;
            }
            if(x > end){
                end = x;
            }

            int y = Integer.parseInt(st.nextToken());

            if(y > best){
                best = y;
                bestIdx = x;
            }
            height[x] = y;

        }
        int maxSize = (end - start + 1) * best;
        //bw.write(maxSize + "\n");
        int answer = maxSize - getLowerValue(height, bestIdx) - getUpperValue(height, bestIdx);

        bw.write(answer + "");
    }

    int getLowerValue(int[] height, int st) throws IOException{
        int total = 0;
        int check = height[st];
        int checkIdx = st;

        while(checkIdx > start){
            int best = 0;
            int bestIdx = 0;
            for(int i = start; i < checkIdx; i++){
                if(height[i] > best){
                    best = height[i];
                    bestIdx = i;
                }
            }
            //bw.write((checkIdx - start) * (check - best) + "\n");
            total += (checkIdx - start) * (check - best);
            checkIdx = bestIdx;
            check = best;
        }
        return total;
    }

    int getUpperValue(int[] height, int st) throws IOException{
        int total = 0;
        int check = height[st];
        int checkIdx = st;

        while(checkIdx < end){
            int best = 0;
            int bestIdx = 0;
            for(int i = checkIdx + 1; i <= end; i++){
                if(height[i] > best){
                    best = height[i];
                    bestIdx = i;
                }
            }
            total += (end - checkIdx) * (check - best);
            //bw.write((end - bestIdx) * (check - best) + "\n");
            //bw.write("checkIdx : " + checkIdx + "\nbestIdx : " + bestIdx + "\n");
            checkIdx = bestIdx;
            check = best;
        }
        return total;
    }
}
