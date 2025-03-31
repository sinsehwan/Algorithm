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
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String data = br.readLine();

            if (data.length() >= m) {
                map.put(data, map.containsKey(data) ? map.get(data) + 1 : 1);
            }
        }

        List<Map.Entry<String, Integer>> li = new ArrayList<>(map.entrySet());
        Collections.sort(li, (o1, o2) -> {
            if(o1.getValue() != o2.getValue()){
                return o2.getValue() - o1.getValue();
            }
            else if(o1.getKey().length() != o2.getKey().length()){
                return o2.getKey().length() - o1.getKey().length();
            }
            else{
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for(Map.Entry<String, Integer> item : li){
            //bw.write(item + "\n");
            bw.write(item.getKey() + "\n");
        }
    }
}