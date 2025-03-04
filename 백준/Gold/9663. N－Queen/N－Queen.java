import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    //place[i] : i번째로 놓은 queen의 가로 위치. 한 row에 퀸 하나씩 배치한다고 생각.
    static int[] place;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        place = new int[n];
        for(int i = 0; i < n; i++){
            place[i] = -1;
        }
        nQueen(0);

        bw.write(count + "");

        br.close();
        bw.close();
    }

    public static void nQueen(int round) throws IOException {
        if(round == n){
            count += 1;
            return;
        }

        for(int i = 0; i < n; i++){
            if(isPromising(round, i)){
                place[i] = round;
                nQueen(round + 1);
                place[i] = -1;
            }
        }
    }

    public static boolean isPromising(int y, int x){
        if(place[x] != -1){
            return false;
        }
        for(int i = 0; i < n; i++){
            if(place[i] != -1){
                // 대각선인 queen이 있으면 불가능.
                if(Math.abs(y - place[i]) == Math.abs(x - i)){
                    return false;
                }
            }
        }

        return true;
    }
}
