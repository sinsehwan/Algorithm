import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int na, nb;

    static int[] arrA;
    static int[] arrB;

    static ArrayList<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        na = Integer.parseInt(st.nextToken());
        nb = Integer.parseInt(st.nextToken());

        arrA = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        arrB = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arrB);
        Arrays.sort(arrA);

        for(int item : arrA){
            if(!binarySearch(item)){
                answerList.add(item);
            }
        }

        bw.write(answerList.size() + "\n");
        for(int item : answerList){
            bw.write(item + " ");
        }

        br.close();
        bw.close();
    }

    public static boolean binarySearch(int target){
        int st = 0;
        int en = nb - 1;

        while(st <= en){
            int mid = st + (en - st) / 2;

            if(arrB[mid] < target){
                st = mid + 1;
            }
            else if(arrB[mid] > target){
                en = mid - 1;
            }
            else{
                return true;
            }
        }

        return false;
    }
}
