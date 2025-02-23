import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr;
    static boolean[] isChecked;
    static LinkedList<Integer> answerList = new LinkedList<>();
    static Set<String> answerSet = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException{
        getInput();

        printPermutations(m);

        for(String data : answerSet){
            bw.write(data + "\n");
        }
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

        isChecked = new boolean[n];
    }

    public static void printPermutations(int remain) throws IOException{
        if(remain == 0){
            StringBuilder temp = new StringBuilder();
            for(int item : answerList){
                temp.append(item).append(" ");
            }
            answerSet.add(temp.toString());
            return;
        }

        for(int i = 0; i < n; i++){
            if(isChecked[i]){
                continue;
            }
            answerList.offerLast(arr[i]);
            isChecked[i] = true;
            printPermutations(remain - 1);
            isChecked[i] = false;
            answerList.pollLast();
        }
    }
}
