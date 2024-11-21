
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int caseNum = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < caseNum; i++)
        {
            arr.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arr, Collections.reverseOrder());
        for(int item : arr){
            bw.write(item + "\n");
        }

        br.close();
        bw.close();
    }
}
