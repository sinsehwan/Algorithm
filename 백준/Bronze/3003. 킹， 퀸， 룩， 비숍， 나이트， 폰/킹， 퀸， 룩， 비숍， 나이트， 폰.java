import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] standard = {1, 1, 2, 2, 2, 8};
        for(int i = 0; i < 6; i++){
            bw.write(standard[i] - Integer.parseInt(st.nextToken()) + " ");
        }

        br.close();
        bw.close();
    }

}
