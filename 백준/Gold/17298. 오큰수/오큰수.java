
import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int caseNum = Integer.parseInt(br.readLine());

        String inputList = br.readLine();

        int[] numList = Arrays.stream(inputList.split(" "))
                        .mapToInt(Integer::parseInt)
                                .toArray();

        Stack<Integer> temp = new Stack<>();

        for (int item : numList)
        {
            temp.add(item);
        }

        Stack<Integer> element = new Stack<>();
        int[] nge = new int[caseNum];
        int ngeIndex = caseNum - 1;

        while(!temp.isEmpty())
        {
            int data = temp.pop();

            while(!element.isEmpty() && element.peek() <= data){
                element.pop();
            }
            if(element.isEmpty())
            {
                nge[ngeIndex] = -1;
            }
            else{
                nge[ngeIndex] = element.peek();
            }
            ngeIndex -= 1;
            element.add(data);
        }

        for (int item : nge){
            bw.write(item + " ");
        }

        br.close();
        bw.close();
    }

}
