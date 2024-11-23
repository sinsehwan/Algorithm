
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int m;
    static int n;

    static boolean[][] available;

    static int[][] vec = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();

    static ArrayList<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int recNum = Integer.parseInt(st.nextToken());

        available = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for (int j = 0; j < n; j++)
            {
                available[i][j] = true;
            }
        }

        for(int i = 0; i < recNum; i++)
        {
            fillRect();
        }
        /*for(int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                bw.write((available[i][j] ? 1 : 0) + "");
            }
            bw.write("\n");
        }
        */


        for(int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                bfs(i, j);
            }
        }

        Collections.sort(answerList);

        bw.write(answerList.size() + "\n");

        for(int i = 0; i < answerList.size(); i++)
        {
            bw.write(answerList.get(i) + " ");
        }

        br.close();
        bw.close();
    }

    static void fillRect() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());

        for(int i = sx; i < ex; i++)
        {
            for(int j = sy; j < ey; j++)
            {
                available[j][i] = false;
            }
        }
    }

    static void bfs(int y, int x){

        if(!available[y][x]){
            return;
        }

        queue.offerLast(new Tuple<>(y, x));
        available[y][x] = false;
        int size = 1;


        while(!queue.isEmpty()){
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++)
            {
                Tuple<Integer, Integer> q = queue.pollFirst();

                for (int[] v : vec){
                    int ny = v[0] + q.getLeft();
                    int nx = v[1] + q.getRight();

                    if(isPromising(ny, nx) && available[ny][nx]){
                        queue.offerLast(new Tuple<>(ny, nx));
                        size += 1;
                        available[ny][nx] = false;
                    }
                }
            }
        }
        answerList.add(size);
    }

    static boolean isPromising(int y, int x){
        return y >= 0 && y < m && x >= 0 && x < n;
    }
}

class Tuple<A, B>{
    private A left;
    private B right;

    Tuple(A left, B right){
        this.left = left;
        this.right = right;
    }

    public A getLeft(){
        return left;
    }

    public B getRight(){
        return right;
    }

    public void setLeft(A left){
        this.left = left;
    }

    public void setRight(B right){
        this.right = right;
    }
}
