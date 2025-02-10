import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> lopes = new ArrayList<>();

        for(int i = 0; i < n; i++){
            lopes.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(lopes);

        int best = 0;
        for(int i = n - 1; i >= 0; i--){
            int lopeNum = n - i;
            int min = lopes.get(i);

            int maxWeight = getMaxWeight(lopeNum, min);
            if(best < maxWeight){
                best = maxWeight;
            }
        }

        bw.write(best + "\n");

        br.close();
        bw.close();
    }

    public static int getMaxWeight(int num, int min){
        return num * min;
    }
}
