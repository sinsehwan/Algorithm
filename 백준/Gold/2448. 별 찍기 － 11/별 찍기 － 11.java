
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static char[][] graph;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        int size = getWidth(n);

        graph = new char[n][size];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < size; j++){
                graph[i][j] = ' ';
            }
        }



        setGraph(0, size / 2, n, size);

        for(char[] li : graph){
            for (char item : li){
                bw.write(item + "");
            }
            bw.write("\n");
        }



        br.close();
        bw.close();
    }

    public static int getWidth(int n){

        n /= 3;

        int count = 0;
        while(n != 1){
            n /= 2;
            count += 1;
        }

        int value = 5;

        for(int i = 0; i < count; i++){
            value = value * 2 + 1;
        }

        return value;
    }

    public static void setGraph(int y, int x, int height, int width){
        //System.out.println("check");
        if(width == 5){
            // * 설정
            graph[y][x] = '*';
            graph[y + 1][x - 1] = '*';
            graph[y + 1][x + 1] = '*';
            for(int i = x - 2; i < x + 3; i++){
                graph[y + 2][i] = '*';
            }
            return;
        }

        int height2 = height / 2;
        int width2 = width / 2;
        int width4 = width / 4;

        setGraph(y, x, height2, width2);
        setGraph(y + height2, x - width4 - 1, height2, width2);
        setGraph(y + height2, x + width4 + 1, height2, width2);
    }
}
