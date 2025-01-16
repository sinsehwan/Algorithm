
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String[] graph;

    public static void main(String[] args) throws IOException {
        int n, m;

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new String[n];

        for(int i = 0; i < n; i++){
            graph[i] = br.readLine();
        }

        int bestCase = 64;
        for(int i = 0; i <= n - 8; i++){
            for(int j = 0; j <= m - 8; j++){
                int value;
                value = checkWB(i, j);
                if(value < bestCase){
                    bestCase = value;
                }
                value = checkBW(i, j);
                if(value < bestCase){
                    bestCase = value;
                }
            }
        }

        bw.write(bestCase + "");

        br.close();
        bw.close();
    }

    public static int checkWB(int y, int x){
        int count = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i + j) % 2 == 0 && graph[y + i].charAt(x + j) == 'W'){
                    count += 1;
                }
                else if((i + j) % 2 == 1 && graph[y + i].charAt(x + j) == 'B'){
                    count += 1;
                }
            }
        }

        return count;
    }

    public static int checkBW(int y, int x){
        int count = 0;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i + j) % 2 == 1 && graph[y + i].charAt(x + j) == 'W'){
                    count += 1;
                }
                else if((i + j) % 2 == 0 && graph[y + i].charAt(x + j) == 'B'){
                    count += 1;
                }
            }
        }

        return count;
    }

}
