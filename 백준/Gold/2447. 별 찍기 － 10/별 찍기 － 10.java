
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static char[][] graph;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        graph = new char[n][n];
        printStar(0, 0, n);

        for(char[] li : graph){
            for(char item : li){
                bw.write(item + "");
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    public static void printStar(int y, int x, int size) throws IOException{
        if(size == 1){
            graph[y][x] = '*';
            return;
        }

        for(int i = y; i < y + size; i+= size/3){
            for(int j = x; j < x + size; j+= size/3){
                if(i == y + size/3 && j == x + size/3){
                    printNone(i, j, size/3);
                }
                else{
                    printStar(i, j, size/3);
                }
            }
        }

    }

    public static void printNone(int y, int x, int size){
        for(int i = y; i < y + size; i++){
            for(int j = x; j < x + size; j++){
                graph[i][j] = ' ';
            }
        }
    }
}
