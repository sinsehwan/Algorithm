import java.io.*;
import java.util.*;

public class Main {

    // 그냥 무지성 dfs
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] li;
    static boolean[] visited;
    static ArrayList<Integer> seq = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        dfs(n);

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        li = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        visited = new boolean[n + 1];
    }

    static void dfs(int remain) throws IOException{
        if(remain == 0){

            if(isAnswer()){
                for(int item : seq){
                    bw.write(item + " ");
                }
            }
            return;
        }

        for(int i = 1; i <= n; i++){
            if(visited[i]){
                continue;
            }
            seq.add(i);
            visited[i] = true;
            dfs(remain - 1);
            seq.remove(seq.size() - 1);
            visited[i] = false;
        }
    }

    static boolean isAnswer() throws IOException{
        int[] tallerList = new int[n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(seq.get(j) > seq.get(i)){
                    tallerList[seq.get(i) - 1] += 1;
                }
            }
        }

        boolean flag = true;
        for(int i = 0; i < n; i++){
            if(li[i] != tallerList[i]){
                flag = false;
                break;
            }
        }
        return flag;
    }
}

