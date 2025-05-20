import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int c;
    ArrayList<Fish> fishList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }
    void solve() throws IOException {
        getInput();

        int best = 0;
        for(int i = 1; i <= 10000; i++){
            int cost = goFish(i);
            if(cost > best){
                best = cost;
            }
        }

        bw.write(best + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            fishList.add(new Fish(x, s, w));
        }

        Collections.sort(fishList, (o1, o2) -> {
            return o2.s - o1.s;
        });
    }

    int goFish(int feed){
        int cost = -c * feed;

        for(Fish fish : fishList){
            if(fish.x <= feed){
                cost += fish.w;
                feed -= fish.x;
            }
        }
        return cost;
    }
}

class Fish{
    int x; // 먹성
    int s; // 크기
    int w; // 가격

    Fish(int x, int s, int w){
        this.x = x;
        this.s = s;
        this.w = w;
    }
}