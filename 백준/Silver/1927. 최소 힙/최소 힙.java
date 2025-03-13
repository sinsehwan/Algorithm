import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());

            if(num == 0){
                if(!pq.isEmpty()){
                    bw.write(pq.poll() + "\n");
                }
                else{
                    bw.write("0\n");
                }
            }
            else{
                pq.offer(num);
            }
        }

        br.close();
        bw.close();
    }
}
