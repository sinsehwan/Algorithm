
import java.io.*;
import java.util.LinkedList;

public class Main {

    static LinkedList<Character> deque = new LinkedList<>();
    static String stick;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        stick = br.readLine();

        getPieces();

        br.close();
        bw.close();
    }

    static void getPieces(){
        int count = 0;
        char previous = '_';
        for(Character item : stick.toCharArray()){
            if (previous == '(' && item == ')'){
                deque.pollLast();
                count += deque.size();
            }
            else if(item == ')'){
                deque.pollLast();
                count += 1;
            }
            else{
                deque.offerLast(item);
            }
            previous = item;
        }

        System.out.println(count);
    }
}
