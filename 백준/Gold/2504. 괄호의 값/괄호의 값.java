
import java.io.*;
import java.util.*;

public class Main {
    static LinkedList<Character> stack = new LinkedList<>();
    static String input = "";
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine();
        if(!isValid())
        {
            System.out.println(0);
        }
        else {
            doCal();
        }
        br.close();
        bw.close();
    }
    static boolean isValid(){
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(stack.isEmpty()){
                if(c == ']' || c == ')'){
                    return false;
                }
                else{
                    stack.offerLast(c);
                }
            }
            else{
                if (stack.getLast() == '(' && c == ')') {
                    stack.pollLast();
                }
                else if(stack.getLast() == '[' && c == ']') {
                    stack.pollLast();
                }
                else{
                    stack.offerLast(c);
                }
            }
        }

        if(stack.isEmpty()){
            return true;
        }
        else{
            stack.clear();
            return false;
        }
    }

    static void doCal(){
        long result = 0;
        int count2 = 0;
        int count3 = 0;
        char previous = '_';

        for(char c : input.toCharArray()){
            if(c == '('){
                count2 += 1;
            }
            else if(c == '[') {
                count3 += 1;
            }
            else if(c == ')')
            {
                count2 -= 1;
                if(previous != ')' && previous != ']')
                {
                    long temp;
                    temp = 2;
                    temp *= getPower(2, count2);
                    temp *= getPower(3, count3);
                    result += temp;
                }
            }
            else if(c == ']')
            {
                count3 -= 1;
                if(previous != ')' && previous != ']')
                {
                    long temp;
                    temp = 3;
                    temp *= getPower(2, count2);
                    temp *= getPower(3, count3);
                    result += temp;
                }
            }
            previous = c;
            //System.out.println(result + " " + count2 + " " + count3);
        }

        System.out.println(result);

    }

    static long getPower(int n, int m){
        long result = 1;
        for(int i = 0; i < m; i++){
            result *= n;
        }
        return result;
    }
}
