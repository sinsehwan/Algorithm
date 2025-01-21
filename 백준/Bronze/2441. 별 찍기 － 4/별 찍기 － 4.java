
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] graph = new char[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i - j <= 0){
                    graph[i][j] = '*';
                }
                else {
                    graph[i][j] = ' ';
                }
            }
        }

        for(char[] li : graph){
            for(char item : li){
                System.out.print(item);
            }
            System.out.println();
        }
    }


}
