import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, s;
    static int[] arr;
    static int answer;

    static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        solve(0);

        bw.write(answer + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void solve(int st) throws IOException{

        if(!list.isEmpty()){
            check();
        }

        for(int i = st; i < n; i++){
            list.offerLast(i);
            solve(i + 1);
            list.pollLast();
        }
    }

    public static void check() throws IOException {
        /*int pre = list.getFirst();
        for(int item : list){
            if(item > pre){
                pre = item;
            }
            else{
                return false;
            }
        }

        return true;

         */

        int total = 0;
        for (int idx : list){
            total += arr[idx];
        }
        if(total == s){
            answer += 1;
        }
    }
}
