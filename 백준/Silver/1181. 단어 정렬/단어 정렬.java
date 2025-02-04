import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet<>();

        for(int i = 0; i < n; i++){
            set.add(br.readLine());
        }

        ArrayList<String> li = new ArrayList<>(set);

        Collections.sort(li, (a, b) -> {
            if(a.length() < b.length()){
                return -1;
            }
            else if(a.length() > b.length()){
                return 1;
            }
            else{
                return a.compareTo(b);
            }
        });


        for(String item : li){
            bw.write(item + "\n");
        }

        br.close();
        bw.close();
    }

}
