
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[n + 1];

        visited[0] = true;
        visited[1] = true;

        Integer count = 0;

        for(int i = 2; i <= n; i++)
        {
            if(count == k){
                break;
            }
            if(!visited[i])
            {
                visited[i] = true;
                count += 1;
                if (count == k){
                    bw.write(i + "");
                    break;
                }

                for (int j = 2*i; j <= n; j += i){
                    if(visited[j]) continue;
                    visited[j] = true;
                    count += 1;
                    if (count == k){
                        bw.write(j + "");
                        break;
                    }
                }

            }
        }

        br.close();
        bw.close();

    }
}
