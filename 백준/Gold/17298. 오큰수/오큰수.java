
import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Integer> stack = new Stack<>();

        int caseNum = Integer.parseInt(br.readLine());

        String input = br.readLine();

        int[] inputArr = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < inputArr.length; i++)
        {
            while(!stack.isEmpty() && inputArr[stack.peek()] < inputArr[i])
            {
                inputArr[stack.pop()] = inputArr[i];
            }

            stack.push(i);
        }

        while(!stack.empty())
        {
            inputArr[stack.pop()] = -1;
        }

        for (int item : inputArr)
        {
            bw.write(item + " ");
        }

        br.close();
        bw.close();
    }
}
