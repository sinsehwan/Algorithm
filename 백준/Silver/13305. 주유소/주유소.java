import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int remain = 0;
    int[] cost;
    int[] soil;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int answer = 0;
        for(int i = 0; i < n - 1; i++){
            int dist = findNext(i);
            //bw.write(dist + " dist\n");

            if(remain < dist){
                int buy = (dist - remain);
                remain += buy;
                answer += buy * soil[i];
                //bw.write(answer + " answer\n");
            }
            remain -= cost[i];
        }
        bw.write(answer + "");
    }

    void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        cost = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        soil = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }

    int findNext(int place) {
        int dist = 0;
        for(int i = place + 1; i < n; i++){
            dist += cost[i - 1];
            if(soil[i] < soil[place]) break;
        }
        return dist;
    }
}
