import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n, m;
    int[][] graph;
    boolean[][][] visited;
    int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new boolean[n][m][2];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        bw.write(bfs() + "");
    }

    int bfs(){
        LinkedList<Triple> queue = new LinkedList<>();

        queue.offerLast(new Triple(0, 0, 1));
        visited[0][0][1] = true;

        int count = 1;
        while(!queue.isEmpty()){
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++){
                Triple node = queue.pollFirst();

                if(node.y == n - 1 && node.x == m - 1){
                    return count;
                }

                for(int[] v : vec){
                    int ny = node.y + v[0];
                    int nx = node.x + v[1];
                    int life = node.life;

                    if(isPromising(ny, nx)){
                        if(life == 0){
                            if(graph[ny][nx] != 1 && !visited[ny][nx][0]) {
                                queue.offerLast(new Triple(ny, nx, 0));
                                visited[ny][nx][0] = true;
                            }
                        }
                        else{
                            if(graph[ny][nx] == 1 && !visited[ny][nx][0]){
                                queue.offerLast(new Triple(ny, nx, 0));
                                visited[ny][nx][0] = true;
                            }
                            else if(graph[ny][nx] == 0 && !visited[ny][nx][1]){
                                queue.offerLast(new Triple(ny, nx, 1));
                                visited[ny][nx][1] = true;
                            }
                        }
                    }
                }
            }
            count += 1;
        }

        return -1;
    }

    boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}

class Triple{
    int y;
    int x;
    int life;

    public Triple(int y, int x, int life){
        this.y = y;
        this.x = x;
        this.life = life;
    }
}