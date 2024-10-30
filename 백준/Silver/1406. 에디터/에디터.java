
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String _char_list = br.readLine();
        LinkedList<Character> char_list = new LinkedList<>();

        ListIterator<Character> corsor = char_list.listIterator();

        int len = _char_list.length();
        for (int i = 0; i < len; i++)
        {
            corsor.add(_char_list.charAt(i));
        }

        int insNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < insNum; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String instruction = st.nextToken();

            if (instruction.charAt(0) == 'P')
            {
                corsor.add(st.nextToken().charAt(0));
            }
            else if (instruction.charAt(0) == 'L'){
                if(corsor.hasPrevious())
                {
                    corsor.previous();
                }
            }
            else if (instruction.charAt(0) == 'D'){
                if (corsor.hasNext())
                {
                    corsor.next();
                }
            }
            else{
                if(corsor.hasPrevious())
                {
                    corsor.previous();
                    corsor.remove();
                }
            }
        }
        for (char item : char_list){
            bw.write(item);
        }
        bw.close();
        br.close();
    }
}
