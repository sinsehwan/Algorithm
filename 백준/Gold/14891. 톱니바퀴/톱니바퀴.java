import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    Wheel[] wheels = new Wheel[4];


    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            rotation(idx, dir);
        }

        int answer = 0;
        for(int i = 0; i < 4; i++){
            answer += wheels[i].arr[(wheels[i].lIdx + 2) % 8] << i;
        }

        bw.write(answer + "");
    }

    void getInput() throws IOException{
        for(int i = 0; i < 4; i++){
            wheels[i] = new Wheel();
            wheels[i].arr = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
            wheels[i].lIdx = 6;
            wheels[i].rIdx = 2;
        }
    }

    void rotation(int idx, int dir){
        leftSide(idx - 1, -dir);
        rightSide(idx + 1, -dir);

        wheels[idx].rotate(dir);
    }

    void leftSide(int idx, int dir){
        if(idx < 0) return;
        if(wheels[idx].arr[wheels[idx].rIdx] == wheels[idx + 1].arr[wheels[idx + 1].lIdx]) return;
        leftSide(idx - 1, -dir);
        wheels[idx].rotate(dir);
    }

    void rightSide(int idx, int dir){
        if(idx > 3) return;
        if(wheels[idx].arr[wheels[idx].lIdx] == wheels[idx - 1].arr[wheels[idx - 1].rIdx]) return;
        rightSide(idx + 1, -dir);
        wheels[idx].rotate(dir);
    }
}

class Wheel {
    int[] arr;
    int lIdx;
    int rIdx;

    void rotate(int dir){
        if(dir == 1) {
            lIdx = (lIdx + 7) % 8;
            rIdx = (rIdx + 7) % 8;
        }
        else{
            lIdx = (lIdx + 1) % 8;
            rIdx = (rIdx + 1) % 8;
        }
    }
}