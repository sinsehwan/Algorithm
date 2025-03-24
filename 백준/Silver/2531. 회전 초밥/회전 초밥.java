import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        int n, d, k, c;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[n];
        int[] type = new int[d + 1];

        for(int i = 0; i < n; i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;

        for(int i = n - k; i < n; i++){
            type[sushi[i]] += 1;
        }

        answer = Math.max(answer, countType(type));

        type[c] += 1;
        for(int i = 0; i < n; i++){
            int j = (i + n - k) % n;
            type[sushi[j]] -= 1;
            type[sushi[i]] += 1;
            answer = Math.max(answer, countType(type));
        }

        bw.write(answer + "");
    }

    int countType(int[] type) {
        int count = 0;
        for(int i = 1; i < type.length; i++){
            if(type[i] > 0){
                count += 1;
            }
        }
        return count;
    }
}
