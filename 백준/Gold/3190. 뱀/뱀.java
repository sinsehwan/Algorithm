import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 인덱스 증가 -> 시계방향
    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    static int n, k, l;
    // 사과 1 빈공간 0
    static int[][] graph;
    static boolean[][] visited;

    static LinkedList<Node> itemList = new LinkedList<>();
    static LinkedList<Node> dirList = new LinkedList<>();
    //현재 방향정보 (초기 : 오른쪽 진행)
    static int dirIdx = 2;
    static LinkedList<Node> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        snake.offerLast(new Node(0, 0));

        int count = 0;
        boolean deleteTail = false;
        while(drawable()){
            //체크 후 꼬리 제거
            if(deleteTail){
                snake.pollFirst();
            }
            count += 1;
            deleteTail = updateSnake(count);
            //System.out.println(count);
        }

        bw.write(count + "");


        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        k = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            //itemList.offerLast(new Node(y - 1, x - 1));
            graph[y - 1][x - 1] = 1;
        }

        l = Integer.parseInt(br.readLine());
        for(int i = 0; i < l; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            if(c == 'L'){
                dirList.offerLast(new Node(time, -1));
            }
            else if(c == 'D'){
                dirList.offerLast(new Node(time, 1));
            }
        }
    }

    public static boolean drawable(){
        visited = new boolean[n][n];

        for (Node e : snake){
            int ey = e.getY();
            int ex = e.getX();

            if(!isPromising(ey, ex) || visited[ey][ex]){
                return false;
            }

            visited[ey][ex] = true;
        }
        /*
        for(boolean[] li : visited){
            for(boolean item : li){
                System.out.print(item + "   ");
            }
            System.out.println();
        }

         */

        return true;
    }
    // 경계 체크
    public static boolean isPromising(int y, int x){
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    public static boolean updateSnake(int count){
        if(!dirList.isEmpty() && dirList.getFirst().getTime() < count){
            //System.out.println(dirList.getFirst().getDir());
            dirIdx = (dirIdx + dirList.getFirst().getDir() + 4) % 4;
            dirList.pollFirst();
        }

        Node head = snake.getLast();

        int ny = head.getY() + dir[dirIdx][0];
        int nx = head.getX() + dir[dirIdx][1];

        boolean deleteTail = false;
        if(isPromising(ny, nx)){
            if(graph[ny][nx] != 1) {
                //아이템 없으면 꼬리 제거
                deleteTail = true;
            }
            else{
                //사과 먹었으면 사과 제거
                graph[ny][nx] = 0;
            }
        }
        //System.out.println("ny : " + ny + ", nx : " + nx);

        snake.offerLast(new Node(ny, nx));

        return deleteTail;
    }
}

class Node {
    int y;
    int x;

    Node(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTime(){
        return y;
    }
    public int getDir(){
        return x;
    }
}
