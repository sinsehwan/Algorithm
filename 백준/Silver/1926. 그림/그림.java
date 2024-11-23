
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] graph;
    static boolean[][] visited;

    static int answer = 0;

    static int[][] vec = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int m, n;

    public static void main(String[] args) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int caseCount = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int result = dfs(i, j);
                if (result != 0){
                    caseCount += 1;
                }
                if (result > answer){
                    answer = result;
                }
            }
        }
        bw.write(caseCount + "\n");
        bw.write(answer + "\n");

        br.close();
        bw.close();
    }

    static int dfs(int y, int x) {
        if(visited[y][x] || graph[y][x] == 0){
            return 0;
        }
        visited[y][x] = true;

        int size = 1;

        for(int[] v : vec){
            int ny = y + v[0];
            int nx = x + v[1];

            if(isPromising(ny, nx) && !visited[ny][nx] && graph[ny][nx] == 1) {
                //System.out.println(ny + " " + nx);
                size += dfs(ny, nx);
            }
        }
        //System.out.println(y +"a " + x + "a " + size);
        return size;
    }

    static boolean isPromising(int y, int x){
        //System.out.println(m+ " "+ y + " " + n + " " + x);
        return y >= 0 && y < m && x >= 0 && x < n;
    }
}
