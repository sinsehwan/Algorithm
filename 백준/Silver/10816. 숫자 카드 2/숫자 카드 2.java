
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static ArrayList<Integer> cards = new ArrayList<>();

    static int[] checkList;

    public static void main(String[] args) throws IOException {
        getInput();

        for(int item : checkList){
            int result = getUpperIdx(item) - getLowerIdx(item);
            bw.write(result + " ");
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            cards.add(Integer.parseInt(st.nextToken()));
        }

        m = Integer.parseInt(br.readLine());

        checkList = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Collections.sort(cards);
    }

    public static int getLowerIdx(int target){
        int st = 0;
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;

            if(cards.get(mid) < target){
                st = mid + 1;
            }
            else{
                en = mid;
            }
        }
        return st;
    }

    public static int getUpperIdx(int target){
        int st = 0;
        int en = n;

        while(st < en){
            int mid = st + (en - st) / 2;

            if(cards.get(mid) <= target){
                st = mid + 1;
            }
            else {
                en = mid;
            }
        }
        return st;
    }
}
