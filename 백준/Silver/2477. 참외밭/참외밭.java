import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int k;
    Move[] info;


    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int answer = 0;

        int maxRow = 0;
        int rIdx = -1;
        int maxCol = 0;
        int cIdx = -1;
        for(int i = 0; i < 6; i++) {
            if(info[i].dir == 1 || info[i].dir == 2) {
                if(maxRow < info[i].dist){
                    maxRow = info[i].dist;
                    rIdx = i;
                }
            }
            else{
                if(maxCol < info[i].dist){
                    maxCol = info[i].dist;
                    cIdx = i;
                }
            }
        }

        int count = maxRow * maxCol;
        int r = (cIdx + 3) % 6;
        int c = (rIdx + 3) % 6;
        count -= info[c].dist * info[r].dist;

        answer = count * k;
        bw.write(answer + "");
    }

    void getInput() throws IOException {
        k = Integer.parseInt(br.readLine());

        info = new Move[6];
        StringTokenizer st;
        for(int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            info[i] = new Move(a, b);
        }
    }
}

class Move{
    int dir;
    int dist;

    public Move(int dir, int dist) {
        this.dir = dir;
        this.dist = dist;
    }
}
