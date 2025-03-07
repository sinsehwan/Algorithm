import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, k;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];

        printK();

        br.close();
        bw.close();
    }

    public static void printK() throws IOException{
        int count = 0;
        for(int i = 2; i <= n; i++){
            int divisor = -1;
            if(visited[i]){
                continue;
            }
            divisor = i;
            visited[i] = true;
            count += 1;
            //bw.write(i + " \n");
            if(count == k){
                bw.write(i + "");
                return;
            }

            for(int j = i * 2; j <= n; j += i){
                if(!visited[j]){
                    visited[j] = true;
                    count += 1;
                    //bw.write(j + " \n");
                    if(count == k){
                        bw.write(j + "");
                        return;
                    }
                }
            }
        }
    }
}
