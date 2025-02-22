import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr;
    static LinkedList<Integer> answerList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        printCombinations(0, m);

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);
    }

    public static void printCombinations(int st, int remain) throws IOException{
        if(remain == 0){
            for(int item : answerList){
                bw.write(item + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i = st; i < n; i++){
            answerList.offerLast(arr[i]);
            printCombinations(i + 1, remain - 1);
            answerList.pollLast();
        }
    }
}
