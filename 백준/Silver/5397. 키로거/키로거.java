
import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++)
        {
            print_password(br, bw);
        }


        br.close();
        bw.close();
    }

    private static void print_password(BufferedReader br, BufferedWriter bw) throws IOException
    {
        LinkedList<Character> password = new LinkedList<>();
        ListIterator<Character> iter = password.listIterator();

        String input_data = br.readLine();

        for (char item : input_data.toCharArray())
        {
            if (item == '<'){
                if (iter.hasPrevious())
                {
                    iter.previous();
                }
            }
            else if (item == '>')
            {
                if (iter.hasNext())
                {
                    iter.next();
                }
            }
            else if (item == '-')
            {
                if(iter.hasPrevious()){
                    iter.previous();
                    iter.remove();
                }
            }
            else{
                iter.add(item);
            }
        }

        for (char item : password)
        {
            bw.write(item);
        }
        bw.write('\n');
    }
}
