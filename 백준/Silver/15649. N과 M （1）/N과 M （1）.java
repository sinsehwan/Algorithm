import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        int[] output = new int[n];
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++)
        {
            arr[i] = i + 1;
        }

        permutation(arr, output, visited, 0, m);

        br.close();
        bw.close();
    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int r){
        if(depth == r){
            for(int i = 0; i < r; i++){
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < arr.length; i++)
        {
            if(!visited[i]){
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, r);
                visited[i] = false;
            }
        }
    }
}
