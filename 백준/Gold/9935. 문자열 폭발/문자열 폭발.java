import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String inputString;
    static StringBuilder sb = new StringBuilder();
    static String explode;


    public static void main(String[] args) throws IOException {
        getInput();

        doExplosion();

        String result = sb.toString();
        if(result.isEmpty()){
            bw.write("FRULA");
        }
        else{
            bw.write(result);
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        inputString = br.readLine();
        explode = br.readLine();
    }

    public static void doExplosion(){
        int totalSize = inputString.length();
        int checkSize = explode.length();

        for(int i = 0; i < totalSize; i++){
            sb.append(inputString.charAt(i));

            while(sb.length() >= checkSize){
                if(sb.substring(sb.length() - checkSize).equals(explode)){
                    sb.setLength(sb.length() - checkSize);
                }
                else{
                    break;
                }
            }
        }
    }
}
