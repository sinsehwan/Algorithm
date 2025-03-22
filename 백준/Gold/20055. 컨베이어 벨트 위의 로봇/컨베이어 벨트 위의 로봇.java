import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[][] convey = new int[n][2];
        // 로봇은 0행에만 존재가능하므로 1차원 배열로.
        boolean[] isRobot = new boolean[n];

        for(int i = 0; i < n; i++){
            convey[i][0] = Integer.parseInt(st.nextToken());
        }

        // 방향 헷갈려서 반대로 넣어둠.
        for(int i = n - 1; i >= 0; i--){
            convey[i][1] = Integer.parseInt(st.nextToken());
        }

        //

        int time = 0;
        do{
            rotate(convey, isRobot);

            //로봇 이동. 마지막 칸 제외. 마지막 칸은 로봇 도착하자마자 내리니까.
            //중요 : 다음 칸에 로봇이 없어야만 이동 가능.
            for(int i = n - 2; i >= 0; i--){
                if(isRobot[i] && convey[i + 1][0] > 0 && !isRobot[i + 1]){
                    convey[i + 1][0] -= 1;
                    isRobot[i + 1] = true;
                    isRobot[i] = false;
                }
            }
            // 올리는 위치 로봇 올리기
            if(convey[0][0] > 0){
                isRobot[0] = true;
                convey[0][0] -= 1;
            }

            time += 1;
        }
        while(isTolerable(n, k, convey));

        bw.write(time + "");
    }

    void rotate(int[][] convey, boolean[] isRobot){
        int n = convey.length;
        // 다음에 1열로 올라갈 노드 내구성 저장.
        int nextRaise = convey[0][1];

        for(int i = 0; i < n - 1; i++){
            convey[i][1] = convey[i + 1][1];
        }

        //내리는 지점
        convey[n - 1][1] = convey[n - 1][0];

        //convey벨트
        for(int i = n - 1; i >= 1; i--){
            convey[i][0] = convey[i - 1][0];
            isRobot[i] = isRobot[i - 1];
        }

        //올리는 지점
        convey[0][0] = nextRaise;
        isRobot[0] = false;

        //중요 : convey 마지막 로봇은 바로 제거해야 함.
        isRobot[n - 1] = false;
    }

    boolean isTolerable(int n, int k, int[][] convey){
        //내구성 0인 애들 count
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                if(convey[i][j] == 0){
                    count += 1;
                }
            }
        }
        return count < k;
    }
}
