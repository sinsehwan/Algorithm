import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    LinkedList<Integer> dirList = new LinkedList<>();

    //좌 상 우 하
    int[][] vec = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    int n, m;
    int[][] graph;

    ArrayList<CCTV> cctvs = new ArrayList<>();
    int cctvNum = 0;
    int answer;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        answer = getBlindNum();

        // cctv 객체 등록
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] >= 1 && graph[i][j] <= 5){
                    cctvs.add(new CCTV(i, j, graph[i][j], 1));
                    cctvNum += 1;
                }
            }
        }

        dfs(4, cctvNum);

        bw.write(answer + "");
    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];

        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    void dfs(int num, int remain){
        if(remain == 0){
            spy();
            int bn = getBlindNum();
            if(bn < answer){
                answer = bn;
            }
            initGraph();
            return;
        }
        for(int i = 0; i < num; i++){
            dirList.offerLast(i);
            dfs(num, remain - 1);
            dirList.pollLast();
        }
    }

    void spy(){
        ArrayList<Integer> dirLi = new ArrayList<>(dirList);
        for(int i = 0; i < cctvNum; i++){
            cctvs.get(i).dir = dirLi.get(i);
            spyOne(cctvs.get(i));
        }
    }

    void spyOne(CCTV c){
        if(c.mode == 1){
            fillLine(c.y, c.x, c.dir);
        }
        else if(c.mode == 2){
            fillLine(c.y, c.x, c.dir);
            fillLine(c.y, c.x, (c.dir + 2) % 4);
        }
        else if(c.mode == 3){
            for(int i = 0; i < 2; i++){
                fillLine(c.y, c.x, c.dir);
                c.dir = (c.dir + 1) % 4;
            }
        }
        else if(c.mode == 4){
            for(int i = 0; i < 3; i++){
                fillLine(c.y, c.x, c.dir);
                c.dir = (c.dir + 1) % 4;
            }
        }
        else{
            // c.mode == 5
            for(int i = 0; i < 4; i++){
                fillLine(c.y, c.x, c.dir);
                c.dir = (c.dir + 1) % 4;
            }
        }
    }

    void initGraph(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == -1){
                    graph[i][j] = 0;
                }
            }
        }
    }

    void fillLine(int y, int x, int dir){
        int ny = y + vec[dir][0];
        int nx = x + vec[dir][1];

        if(isPromising(ny, nx) && graph[ny][nx] != 6){
            fillLine(ny, nx, dir);
            if(graph[ny][nx] == 0){
                graph[ny][nx] = -1;
            }
        }
    }

    boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    int getBlindNum(){
        int bNum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == 0){
                    bNum += 1;
                }
            }
        }
        return bNum;
    }
}

class CCTV{
    int y;
    int x;
    int mode;
    int dir;

    public CCTV(int y, int x, int mode, int dir){
        this.y = y;
        this.x = x;
        this.mode = mode;
        this.dir = dir;
    }
}