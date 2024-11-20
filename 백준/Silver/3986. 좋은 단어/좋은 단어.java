
import java.io.*;
import java.util.LinkedList;

public class Main {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int caseNum = Integer.parseInt(br.readLine());
        for(int i = 0; i < caseNum; i++)
        {
            char[] charList = br.readLine().toCharArray();
            doStack(charList);
        }
        bw.write(count + "");
        br.close();
        bw.close();
    }

    static void doStack(char[] charList){
        LinkedList<Character> deque = new LinkedList<>();

        for (char item : charList){
            if(deque.isEmpty()){
                deque.offerLast(item);
            }
            else if(deque.getLast().equals(item)){
                deque.pollLast();
            }
            else{
                deque.offerLast(item);
            }
        }

        if (deque.isEmpty()){
            count += 1;
        }
    }
}
