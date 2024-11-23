
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int mapSize;
    //static int[][] graph;
    static boolean[][] visited;

    static int vec[][] = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    public static void main(String[] args) throws IOException{
        int caseNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseNum; i++)
        {
            mapSize = Integer.parseInt(br.readLine());

            //graph = new int[mapSize][mapSize];
            visited = new boolean[mapSize][mapSize];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int ey = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());

            bw.write(bfs(sy, sx, ey, ex) + "\n");
        }

        br.close();
        bw.close();
    }

    static int bfs(int sy, int sx, int ey, int ex) throws IOException{
        LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();
        int count = 0;

        queue.offerLast(new Tuple<>(sy, sx));

        while(!queue.isEmpty()){
            int qSize = queue.size();
            for(int i = 0; i < qSize; i++)
            {
                Tuple<Integer, Integer> q = queue.pollFirst();

                if (q.getLeft() == ey && q.getRight() == ex){
                    return count;
                }

                for (int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && !visited[ny][nx]){
                        queue.offerLast(new Tuple<>(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }

            count += 1;
        }

        return count;
    }

    static boolean isPromising(int y, int x){
        return y >= 0 && y < mapSize && x >= 0 && x < mapSize;
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
