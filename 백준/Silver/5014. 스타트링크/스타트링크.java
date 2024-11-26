
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int m, s, g, u, d;

    static int[] vec;

    static LinkedList<Integer> queue = new LinkedList<>();

    static boolean[] visited;

    public static void main(String[] args) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        visited = new boolean[m + 1];
        vec = new int[]{u, -d};

        int result = bfs(s, g);

        if(result == -1){
            bw.write("use the stairs");
        }
        else{
            bw.write(result + "");
        }

        br.close();
        bw.close();
    }

    static int bfs(int st, int en){
        int count = 0;
        queue.offerLast(st);

        while(!queue.isEmpty()){
            int qSize = queue.size();


            for(int i = 0; i < qSize; i++){
                int q = queue.pollFirst();

                if (q == en){
                    return count;
                }

                for (int item : vec){
                    int nq = q + item;

                    if (isPromising(nq) && !visited[nq]){
                        visited[nq] = true;
                        queue.offerLast(nq);
                    }
                }
            }
            count += 1;
        }

        return -1;
    }

    static boolean isPromising(int x){
        return x > 0 && x <= m;
    }
}
