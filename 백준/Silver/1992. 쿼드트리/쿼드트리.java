
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] graph;

    static LinkedList<Character> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException{

        int n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        quadTree(0, 0, n);

        for (char item : queue){
            bw.write(item + "");
        }

        br.close();
        bw.close();
    }

    public static void quadTree(int y, int x, int size) throws IOException {
        int num = graph[y][x];

        for(int i = y; i < y + size; i++){
            for(int j = x; j < x + size; j++){
                if(graph[i][j] != num){
                    queue.offerLast('(');
                    quadTree(y, x, size/2);
                    quadTree(y, x + size/2, size/2);
                    quadTree(y + size/2, x, size/2);
                    quadTree(y + size/2, x + size/2, size/2);
                    queue.offerLast(')');
                    return;
                }
            }
        }

        queue.offerLast((char) (num + '0'));
    }
}
