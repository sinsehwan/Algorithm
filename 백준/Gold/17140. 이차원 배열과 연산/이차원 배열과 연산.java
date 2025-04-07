import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int r, c, k;
    int y, x;
    int[][] arr = new int[101][101];

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        getInput();

        for(int i = 0; i <= 100; i++){
            boolean isSame = check();

            if(isSame){
                bw.write(i + "");
                return;
            }
            calculate();
        }
        bw.write("-1");
    }

    void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        y = 3;
        x = 3;
    }

    boolean check(){
        return arr[r][c] == k;
    }

    void calculate() {
        if(y >= x){
            // R연산 적용
            Map<Integer, Integer> countMap;
            int maxSize = 0;

            for(int i = 1; i <= y; i++){
                countMap = new HashMap<>();

                for(int j = 1; j <= x; j++){
                    int num = arr[i][j];
                    if(num == 0) continue;
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                }

                List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
                ArrayList<Integer> newList = new ArrayList<>();

                Collections.sort(entryList, (e1, e2) -> {
                   if(e1.getValue() != e2.getValue()){
                       return e1.getValue().compareTo(e2.getValue());
                   }
                   else{
                       return e1.getKey().compareTo(e2.getKey());
                   }
                });

                for(Map.Entry<Integer, Integer> entry : entryList){
                    newList.add(entry.getKey());
                    newList.add(entry.getValue());
                }

                if(newList.size() > maxSize){
                    maxSize = Math.min(100, newList.size());
                }

                for(int j = 1; j <= Math.min(newList.size(), 100); j++){
                    arr[i][j] = newList.get(j - 1);
                }
                for(int j = newList.size() + 1; j <= 100; j++){
                    arr[i][j] = 0;
                }
            }
            x = maxSize;
        }
        else{
            // C 연산 적용
            Map<Integer, Integer> countMap;
            int maxSize = 0;
            for(int j = 1; j <= x; j++){
                countMap = new HashMap<>();

                for(int i = 1; i <= y; i++){
                    int num = arr[i][j];
                    if(num == 0) continue;
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                }

                List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
                List<Integer> newList = new ArrayList<>();

                Collections.sort(entryList, (e1, e2) -> {
                   if(e1.getValue() != e2.getValue()){
                       return e1.getValue().compareTo(e2.getValue());
                   }
                   else{
                       return e1.getKey().compareTo(e2.getKey());
                   }
                });
                for(Map.Entry<Integer, Integer> entry : entryList){
                    newList.add(entry.getKey());
                    newList.add(entry.getValue());
                }

                if(newList.size() > maxSize){
                    maxSize = Math.min(100, newList.size());
                }
                for(int i = 1; i <= Math.min(100, newList.size()); i++){
                    arr[i][j] = newList.get(i - 1);
                }
                for(int i = newList.size() + 1; i <= 100; i++){
                    arr[i][j] = 0;
                }
            }

            y = maxSize;
        }
    }
}
