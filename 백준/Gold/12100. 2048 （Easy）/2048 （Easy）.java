import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[][] graph;
    // 상 하 좌 우
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},};
    static boolean[][] merged;

    static LinkedList<LinkedList<Integer>> moveList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        setMoveList(5, new LinkedList<Integer>());

        bw.write(getAnswer() + "");

        br.close();
        bw.close();
    }

    static void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        merged = new boolean[n][n];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    static void setMoveList(int remain, LinkedList<Integer> li) {
        if(remain == 0){
            moveList.offerLast(new LinkedList<>(li));
            return;
        }
        for(int i = 0; i < 4; i++){
            li.offerLast(i);
            setMoveList(remain - 1, li);
            li.pollLast();
        }
    }

    static int getAnswer() throws IOException{
        int bestSize = 0;
        for(LinkedList<Integer> ml : moveList){
            int[][] g = new int[n][n];
            init(g);

            for(int idx : ml){
                merged = new boolean[n][n];
                //bw.write(idx + " ");
                move(idx, g);
            }
            //bw.write("\n");

            /*
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    bw.write(g[i][j] + " ");
                }
                bw.write("\n");
            }
            bw.write("\n");
             */


            int size = getBest(g);
            if(size > bestSize){
                bestSize = size;
            }
        }

        return bestSize;
    }

    static void init(int[][] g) {
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                g[i][j] = graph[i][j];
            }
        }
    }

    static void move(int idx, int[][] g) throws IOException {
        //상
        if(idx == 0)
        {
            for(int j = 0; j < n; j++){
                int curPlace = 0;
                for(int i = 0; i < n; i++){
                    if(g[i][j] != 0){
                        int[] joint = {curPlace - 1, j};
                        if(mergeIfPossible(joint, i, j, g)){
                        }
                        else{
                            //병합은 불가능한 경우
                            if(curPlace != i){
                                g[curPlace][j] = g[i][j];
                                g[i][j] = 0;
                            }
                            curPlace += 1;
                        }
                    }
                }
            }
        }
        // 하
        else if(idx == 1){
            for(int j = 0; j < n; j++){
                int curPlace = n - 1;
                for(int i = n - 1; i >= 0; i--){
                    if(g[i][j] != 0){
                        int[] joint = {curPlace + 1, j};
                        if(mergeIfPossible(joint, i, j, g)){
                        }
                        else{
                            if(curPlace != i){
                                g[curPlace][j] = g[i][j];
                                g[i][j] = 0;
                            }
                            curPlace -= 1;
                        }
                    }
                }
            }
        }
        // 좌
        else if(idx == 2){
            for(int i = 0; i < n; i++){
                int curPlace = 0;
                for(int j = 0; j < n; j++){
                    if(g[i][j] != 0){
                        int[] joint = {i, curPlace - 1};
                        if(mergeIfPossible(joint, i, j, g)){
                        }
                        else{
                            if(curPlace != j){
                                g[i][curPlace] = g[i][j];
                                g[i][j] = 0;
                            }
                            curPlace += 1;
                        }
                    }
                }
            }
        }
        // 우
        else{
            for(int i = 0; i < n; i++){
                int curPlace = n - 1;
                for(int j = n - 1; j >= 0; j--){
                    if(g[i][j] != 0){
                        int[] joint = {i, curPlace + 1};
                        if(mergeIfPossible(joint, i, j, g)){
                        }
                        else{
                            if(curPlace != j){
                                g[i][curPlace] = g[i][j];
                                g[i][j] = 0;
                            }
                            curPlace -= 1;
                        }
                    }
                }
            }
        }
    }

    static boolean mergeIfPossible(int[] joint, int i, int j, int[][] g){
        if(isPromising(joint[0], joint[1]) && g[joint[0]][joint[1]] == g[i][j] && !merged[joint[0]][joint[1]]){
            //병합가능한 경우
            g[joint[0]][joint[1]] *= 2;
            g[i][j] = 0;
            merged[joint[0]][joint[1]] = true;
            return true;
        }
        else{
            return false;
        }
    }

    static boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    static int getBest(int[][] g){
        int best = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(g[i][j] > best){
                    best = g[i][j];
                }
            }
        }
        return best;
    }

}
