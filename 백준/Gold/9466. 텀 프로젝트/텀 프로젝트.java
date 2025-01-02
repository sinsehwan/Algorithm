
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static int[] graph;
    static int[] visited;

    static LinkedList<Integer> queue;

    public static void main(String[] args) throws IOException{
        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            n = Integer.parseInt(br.readLine());

            graph = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < graph.length; j++){
                graph[j] -= 1;
            }

            visited = new int[n];

            queue = new LinkedList<>();

            for(int j = 0; j < n; j++){
                if(visited[j] == 0){
                    queue.offerLast(j);
                    visited[j] = -2;
                    dfs(graph[j]);
                }
            }

            int count = 0;

            for(int j = 0; j < n; j++){
                //System.out.print(visited[j] + " ");
                if(visited[j] == -1){
                    count += 1;
                }
            }
            bw.write(count + "\n");
        }

        br.close();
        bw.close();
    }

    public static void dfs(int num){
        if(visited[num] == -1 || visited[num] == 1){
            while(!queue.isEmpty()){
                int item = queue.pollFirst();
                visited[item] = -1;
            }
        }
        else if(queue.peekFirst() == num){
            while(!queue.isEmpty()){
                int item = queue.pollFirst();
                visited[item] = 1;
            }
        }
        else if(visited[num] == -2){
            int item = queue.pollFirst();
            visited[item] = -1;
            dfs(num);
        }
        else{
            visited[num] = -2;
            queue.offerLast(num);
            dfs(graph[num]);
        }
    }
}
