import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String table = br.readLine();

        LinkedList<Integer> ham = new LinkedList<>();
        LinkedList<Integer> peo = new LinkedList<>();

        int answer = 0;
        for(int i = 0; i < table.length(); i++){
            if(table.charAt(i) == 'H'){
                ham.offerLast(i);
            }
            else{
                peo.offerLast(i);
            }
        }

        while(!ham.isEmpty() && !peo.isEmpty()){
            int hIdx = ham.getFirst();
            int pIdx = peo.getFirst();

            if(Math.abs(hIdx - pIdx) <= k){
                answer += 1;
                ham.pollFirst();
                peo.pollFirst();
            }
            else{
                if(hIdx < pIdx){
                    ham.pollFirst();
                }
                else{
                    peo.pollFirst();
                }
            }
        }
        bw.write(answer + "");
    }
}
