import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static HashMap<Character, Integer> dict = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int s, p;
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        String dna = br.readLine();

        int[] check = new int[4];
        int[] count = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            check[i] = Integer.parseInt(st.nextToken());
        }

        dict.put('A', 0);
        dict.put('C', 1);
        dict.put('G', 2);
        dict.put('T', 3);
        new Main().solve(s, p, dna, count, check);

        br.close();
        bw.close();
    }


    void solve(int s, int p, String dna, int[] count, int[] check) throws IOException {
        int answer = 0;
        for(int i = 0; i <= s - p; i++){
            boolean isGood = slidingWindow(p, dna, i, count, check);
            if(isGood){
                answer += 1;
            }
        }

        bw.write(answer + "");
    }

    boolean slidingWindow(int p, String dna, int st, int[] count, int[] check){
        if(st == 0){
            for(int i = st; i < st + p; i++){
                Integer idx = dict.get(dna.charAt(i));
                count[idx] += 1;
            }
        }
        else{
            cache(p, dna, st, count);
        }

        return isSufficient(count, check);
    }

    void cache(int p, String dna, int st, int[] count) {
        char remove = dna.charAt(st - 1);
        char add = dna.charAt(st + p - 1);

        count[dict.get(remove)] -= 1;
        count[dict.get(add)] += 1;
    }

    boolean isSufficient(int[] count, int[] check){
        for(int i = 0; i < 4; i++){
            if(count[i] < check[i]){
                return false;
            }
        }
        return true;
    }
}
