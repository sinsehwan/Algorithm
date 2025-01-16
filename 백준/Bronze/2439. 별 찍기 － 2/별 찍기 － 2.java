import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{

        int n = Integer.parseInt(br.readLine());

        char[][] stars = new char[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i + j < n - 1){
                    bw.write(" ");
                }
                else{
                    bw.write("*");
                }
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

}
