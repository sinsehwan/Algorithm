
import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Pair<Integer, Integer>> stack = new Stack<>();

        int topNum = Integer.parseInt(br.readLine());

        String inputList = br.readLine();

        int[] numList = Arrays.stream(inputList.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] resultList = new int[topNum];

        for (int i = 0; i < topNum; i++)
        {
            while(!stack.isEmpty() && stack.peek().getLeft() < numList[i]){
                stack.pop();
            }

            if (stack.isEmpty())
            {
                resultList[i] = 0;
            }
            else{
                resultList[i] = stack.peek().getRight() + 1;
            }
            stack.add(new Pair<>(numList[i], i));//Pair.create(numList[i], i));
        }

        for(int item : resultList)
        {
            bw.write(item + " ");
        }
        br.close();
        bw.close();
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second)
    {
        this.first = first;
        this.second = second;
    }

    public A getLeft(){
        return first;
    }
    public B getRight() {
        return second;
    }
    public void setLeft(A first)
    {
        this.first = first;
    }
    public void setRight(B second)
    {
        this.second = second;
    }
}
