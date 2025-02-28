import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    //0이면 u, 1이면 d, 2이면 l, 3이면 r
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] graph;
    static int[][] visited;
    static LinkedList<Node> updateList = new LinkedList<>();

    static int count = 0;

    public static void main(String[] args) throws IOException {
        getInput();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j] != 1){
                    dfs(i, j);

                    visitUpdate();
                }
            }
        }

        bw.write(count + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];

        for(int i = 0; i < n; i++){
            String input = br.readLine();

            for(int j = 0; j < m; j++){
                if(input.charAt(j) == 'U'){
                    graph[i][j] = 0;
                }
                else if(input.charAt(j) == 'D'){
                    graph[i][j] = 1;
                }
                else if(input.charAt(j) == 'L'){
                    graph[i][j] = 2;
                }
                else{
                    graph[i][j] = 3;
                }
            }
        }
        visited = new int[n][m];
    }

    public static void dfs(int y, int x){
        if(visited[y][x] == 1){
            return;
        }
        else if(visited[y][x] == -1){
            //진행중인 곳으로 회귀한 경우
            count += 1;
            return;
        }

        visited[y][x] = -1;
        updateList.add(new Node(y, x));
        int ny = y + dir[graph[y][x]][0];
        int nx = x + dir[graph[y][x]][1];
        dfs(ny, nx);
    }

    public static boolean isPromising(int y, int x){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void visitUpdate(){
        for (Node n : updateList){
            visited[n.getY()][n.getX()] = 1;
        }
        updateList.clear();
    }
}

class Node{
    private int y;
    private int x;

    public Node(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
