import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException{
        String str = "";

        while(true){
            str = br.readLine();
            if(str.equals("end")) break;
            boolean result = checkString(str);
            if(result){
                bw.write("<" + str + "> is acceptable.\n");
            }
            else{
                bw.write("<" + str + "> is not acceptable.\n");
            }
        }
    }

    boolean checkString(String str){
        boolean checkVowel = false;

        int conCount = 0;
        int vowCount = 0;
        char preChar = '\0';
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for(char c : str.toCharArray()){
            if(preChar == c && c != 'e' && c != 'o'){
                return false;
            }

            if(vowels.contains(c)){
                checkVowel = true;
                vowCount += 1;
                conCount = 0;
                if(vowCount >= 3){
                    return false;
                }
            }
            else{
                vowCount = 0;
                conCount += 1;
                if(conCount >= 3){
                    return false;
                }
            }
            preChar = c;
        }
        if(!checkVowel){
            return false;
        }
        return true;


    }
}
