
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int l, r, c;

    static char[][][] graph;
    static boolean[][][] visited;

    static int[][] vec = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};

    public static void main(String[] args) throws IOException{
        while(true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());


            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            //System.out.println(l + r + c);
            if(l == 0 && r == 0 && c == 0){
                break;
            }

            graph = new char[l][r][c];
            visited = new boolean[l][r][c];

            for(int i = 0; i < l; i++){
                for(int j = 0; j < r; j++){
                    graph[i][j] = br.readLine().toCharArray();
                }
                br.readLine();
            }


            int result = bfs();

            if(result == -1){
                bw.write("Trapped!\n");
            }
            else{
                bw.write("Escaped in " + result + " minute(s).\n");
            }
        }


        br.close();
        bw.close();
    }

    static int bfs(){
        LinkedList<Triple<Integer, Integer, Integer>> queue = new LinkedList<>();

        int count = 0;

        int sz = 0, sy = 0, sx = 0;
        int ez = 0, ey = 0, ex = 0;
        for(int i = 0; i < l; i++){
            for(int j = 0; j < r; j++){
                for(int k = 0; k < c; k++){
                    if(graph[i][j][k] == 'S'){
                        sz =  i;
                        sy = j;
                        sx = k;
                    }
                    else if(graph[i][j][k] == 'E'){
                        ez = i;
                        ey = j;
                        ex = k;
                    }
                }
            }
        }
        queue.offerLast(new Triple<>(sz, sy, sx));
        visited[sz][sy][sx] = true;

        while(!queue.isEmpty()){
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++){
                Triple<Integer, Integer, Integer> q = queue.pollFirst();

                if(q.getFirst() == ez && q.getSecond() == ey && q.getThird() == ex){
                    return count;
                }

                for(int[] v : vec){
                    int nz = q.getFirst() + v[0];
                    int ny = q.getSecond() + v[1];
                    int nx = q.getThird() + v[2];

                    if(isPromising(nz, ny, nx) && !visited[nz][ny][nx] && graph[nz][ny][nx] != '#'){
                        visited[nz][ny][nx] = true;
                        queue.offerLast(new Triple<>(nz, ny, nx));
                    }
                }
            }
            count += 1;
        }
        return -1;
    }

    static boolean isPromising(int z, int y, int x){
        return z >= 0 && z < l && y >= 0 && y < r && x >= 0 && x < c;
    }
}

class Triple<A, B, C>{
    private A first;
    private B second;
    private C third;
    Triple(A first, B second, C third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst(){
        return first;
    }

    public B getSecond(){
        return second;
    }

    public C getThird() {
        return third;
    }

}