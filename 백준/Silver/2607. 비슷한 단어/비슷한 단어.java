import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    String ori;
    String[] comp;

    Map<Character, Integer> oris = new HashMap<>();
    Map<Character, Integer> comps = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        getInput();

        for(int i = 0; i < ori.length(); i++){
            oris.put(ori.charAt(i), oris.getOrDefault(ori.charAt(i), 0)  +1);
        }

        int count = 0;
        for(int i = 0; i < n - 1; i++){
            if(check(i)){
                count += 1;
            }
        }

        bw.write(count + "");
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        ori = br.readLine();
        comp = new String[n - 1];
        for(int i = 0; i < n - 1; i++){
            comp[i] = br.readLine();
        }
    }

    boolean check(int idx){
        comps.clear();

        for(int i = 0; i < comp[idx].length(); i++){
            comps.put(comp[idx].charAt(i), comps.getOrDefault(comp[idx].charAt(i), 0) + 1);
        }

        int dif = 0;
        Set<Character> allChars = new HashSet<>();
        allChars.addAll(oris.keySet());
        allChars.addAll(comps.keySet());

        for (char c : allChars) {
            int a = oris.getOrDefault(c, 0);
            int b = comps.getOrDefault(c, 0);
            dif += Math.abs(a - b);
        }
        // 총 길이 차이 계산
        int lenDiff = Math.abs(ori.length() - comp[idx].length());

        if(dif <= 2 && lenDiff <= 1){
            return true;
        }
        else{
            return false;
        }
    }
}
