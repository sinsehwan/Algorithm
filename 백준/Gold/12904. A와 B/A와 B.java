import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String s1;
    String s2;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        Str target = new Str(s2);
        while(target.getStr().length() > s1.length()){
            if(target.getLast() == 'A'){
                target.removeLast();
            }
            else{
                target.removeLast();
                target.reverse();
            }
            //System.out.println(target.getStr());
        }

        if(target.getStr().equals(s1)){
            bw.write("1");
        }
        else{
            bw.write("0");
        }
    }

    void getInput() throws IOException {
        s1 = br.readLine();
        s2 = br.readLine();
    }
}

class Str{
    private LinkedList<Character> str = new LinkedList<>();
    private int mode;
    public Str(String s){
        for(char item : s.toCharArray()){
            str.offerLast(item);
        }
        mode = 0;
    }
    public void reverse(){
        mode = (mode + 1) % 2;
    }

    public char getLast(){
        if(mode == 0){
            return str.getLast();
        }
        else{
            return str.getFirst();
        }
    }

    public void removeLast(){
        if(mode == 0){
           str.pollLast();
        }
        else{
            str.pollFirst();
        }
    }

    public String getStr(){
        if(mode == 1) {
            Collections.reverse(str);
            mode = 0;
        }

        StringBuilder result = new StringBuilder();
        for(char item : str){
            result.append(item);
        }
        return result.toString();
    }
}
