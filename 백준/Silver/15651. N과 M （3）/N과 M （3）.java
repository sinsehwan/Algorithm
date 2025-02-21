import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;

    static LinkedList<Integer> seq = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        printSequence(1, m);

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    public static void printSequence(int st, int remain) throws IOException{
        if(remain == 0){
            for(int item : seq){
                bw.write(item + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i = st; i <= n; i++){
            seq.offerLast(i);
            printSequence(st, remain - 1);
            seq.pollLast();
        }
    }
}
