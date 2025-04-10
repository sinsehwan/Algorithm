import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[][] graph;
    boolean[][] visited;
    // 가장 위, 가장 왼쪽 물고기 우선 탐색. 상 좌 우 하
    int[][] vec = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    int y = 0, x = 0;
    int sharkSize = 2;
    int sharkExp = 0;
    int fishNum = 0;
    int time;

    public static void main(String[] args) throws IOException{
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        while(fishNum > 0){
            int turnTime = eatNext();
            if(turnTime == -1){
                break;
            }
            else{
                time += turnTime;
                initVisit();
            }
        }
        bw.write(time + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] == 9){
                    y = i;
                    x = j;
                    // 상어 확인 후 9 값 버리기
                    graph[i][j] = 0;
                }
                else if(graph[i][j] > 0){
                    fishNum += 1;
                }
            }
        }
    }

    int eatNext(){
        LinkedList<Node> queue = new LinkedList<>();

        int dist = 0;

        queue.offerLast(new Node(y, x));
        while(!queue.isEmpty()){
            int qSize = queue.size();

            ArrayList<Node> temp = new ArrayList<>(queue);
            Collections.sort(temp, (o1, o2) -> {
                if(o1.y != o2.y){
                    return o1.y - o2.y;
                }
                else{
                    return o1.x - o2.x;
                }
            });
            queue = new LinkedList<>(temp);

            for(int i = 0; i < qSize; i++){
                Node n = queue.pollFirst();

                int fSize = graph[n.y][n.x];
                if(fSize > 0 && fSize < sharkSize){
                    fishNum -= 1;
                    graph[n.y][n.x] = 0;
                    y = n.y;
                    x = n.x;
                    sharkExp += 1;
                    if(sharkExp == sharkSize){
                        sharkExp -= sharkSize;
                        sharkSize += 1;
                    }
                    return dist;
                }

                for(int[] v : vec){
                    int ny = n.y + v[0];
                    int nx = n.x + v[1];

                    // 크기가 같은 물고기 지나가는 것은 가능
                    if(isPromising(ny, nx) && graph[ny][nx] <= sharkSize && !visited[ny][nx]){
                        visited[ny][nx] = true;
                        queue.offerLast(new Node(ny, nx));
                    }
                }
            }
            dist += 1;
        }
        return -1;
    }

    boolean isPromising(int y, int x){
        return y < n && y >= 0 && x < n && x >= 0;
    }

    void initVisit(){
        visited = new boolean[n][n];
    }
}
class Node{
    int y, x;

    public Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}
