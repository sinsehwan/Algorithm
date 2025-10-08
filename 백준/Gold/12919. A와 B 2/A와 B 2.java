import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String origin;
    String target;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        boolean valid = bfs(target);

        if (valid) {
            bw.write("1");
        }
        else{
            bw.write("0");
        }
    }

    void getInput() throws IOException {
        origin = br.readLine();
        target = br.readLine();
    }

    boolean bfs(String target) throws IOException {
        LinkedList<String> queue = new LinkedList<>();

        queue.offerLast(target);

        while(!queue.isEmpty()) {
            int qSize = queue.size();
            //bw.write(qSize + "\n");
            //for(String str : queue) {
            //    bw.write(str + " ");
            //}
            //bw.write("\n");

            for (int i = 0; i < qSize; i++) {
                String str = queue.pollFirst();

                if(origin.equals(str)) {
                    return true;
                }

                if (str.length() > origin.length()) {
                    if (str.charAt(0) == 'B') {
                        queue.offerLast(reverse(str.substring(1)));
                    }

                    if (str.charAt(str.length() - 1) == 'A') {
                        queue.offerLast(str.substring(0, str.length() - 1));
                    }
                }
            }
        }

        return false;
    }

    String reverse(String str) {
        char[] chars = str.toCharArray();

        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }
}
