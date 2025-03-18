import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

    void solve() throws IOException{
        Set<String> people = new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String s = st.nextToken();
        int mode = 0;
        if(s.charAt(0) == 'Y'){
            mode = 1;
        }
        else if(s.charAt(0) == 'F'){
            mode = 2;
        }
        else{
            mode = 3;
        }

        for(int i = 0; i < n; i++){
            people.add(br.readLine());
        }
        int count = people.size();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //bw.write(count + "\n");
        count = count / mode;
        bw.write(count + "");

        br.close();
        bw.close();
    }
}
