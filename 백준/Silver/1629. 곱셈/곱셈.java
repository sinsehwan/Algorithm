import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] li = new int[32];
        long temp = a;
        li[0] = a % c;
        for(int i = 1; i < 32; i++){
            temp = (long)temp * temp % c;
            li[i] = (int)temp;
        }

        LinkedList<Integer> queue = new LinkedList<>();

        for(int i = 0; i < 32; i++){
            queue.offerLast(b % 2);
            b /= 2;
        }

        long answer = 1;
        for(int i = 0; i < 32; i++){
            int check = queue.pollFirst();
            if (check == 1){
                answer = (long)li[i] * answer % c;
            }
        }

        System.out.println(answer);
    }
}
