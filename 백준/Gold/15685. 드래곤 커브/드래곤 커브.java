import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    static ArrayList<Integer>[][] curveList;
    static int[][] curves;
    static int[][] graph = new int[101][101];

    public static void main(String[] args) throws IOException {
        getInput();

        makeCurveList();
        for(int[] c : curves){

            drawCurve(c[1], c[0], c[2], c[3]);
        }

        bw.write(countAnswer() + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        curves = new int[n][4];
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                curves[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void makeCurveList(){
        curveList = new ArrayList[4][11];

        for(int i = 0; i < 4; i++){
            curveList[i][0] = new ArrayList<>();
            curveList[i][0].add(i);
        }
        for(int i = 0; i < 4; i++){
            for(int j = 1; j < 11; j++){
                curveList[i][j] = new ArrayList<>(curveList[i][j - 1]);
                LinkedList<Integer> stack = new LinkedList<>(curveList[i][j - 1]);
                while(!stack.isEmpty()){
                    curveList[i][j].add((stack.pollLast() + 1) % 4);
                }
            }
        }
    }

    public static void drawCurve(int y, int x, int d, int g){
        graph[y][x] = 1;
        for(int idx : curveList[d][g]){
            int ny = y + dir[idx][0];
            int nx = x + dir[idx][1];
            graph[ny][nx] = 1;
            y = ny;
            x = nx;
        }
    }

    public static int countAnswer(){
        int count = 0;

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(isRectangle(i, j)){
                    count += 1;
                }
            }
        }
        return count;
    }

    public static boolean isRectangle(int y, int x){
        return graph[y][x] == 1 &&
                graph[y + 1][x] == 1 &&
                graph[y][x + 1] == 1 &&
                graph[y + 1][x + 1] == 1;
    }
}

class Tuple{
    private int y;
    private int x;

    public Tuple(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getY(){
        return y;
    }
    public int getX() {
        return x;
    }
}
