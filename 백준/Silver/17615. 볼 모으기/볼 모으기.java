import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        solve();



        br.close();
        bw.close();
    }

    static void solve() throws IOException{
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        int r = getRedBest(input, n);
        int b = getBlueBest(input, n);
        bw.write(Math.min(r, b) + "");
    }

    static int getRedBest(String str, int size) throws IOException{
        //우측 몰기
        boolean b = false;
        int c1 = 0;
        for(int i = size - 1; i >= 0; i--){
            //bw.write(str.charAt(i) + "");
            if(str.charAt(i) == 'B'){
                b = true;
            }
            else if(str.charAt(i) == 'R' && b){
                c1 += 1;
            }
        }

        //좌측
        b = false;
        int c2 = 0;
        for(int i = 0; i < size; i++){
            if(str.charAt(i) == 'B'){
                b = true;
            }
            else if(str.charAt(i) == 'R' && b){
                c2 += 1;
            }
        }
        //bw.write("c1 : " + c1 + ", c2 : " + c2);
        return Math.min(c1, c2);

    }

    static int getBlueBest(String str, int size) throws IOException{
        //우측 몰기
        boolean b = false;
        int c1 = 0;
        for(int i = size - 1; i >= 0; i--){
            if(str.charAt(i) == 'R'){
                b = true;
            }
            else if(str.charAt(i) == 'B' && b){
                c1 += 1;
            }
        }

        //좌측
        b = false;
        int c2 = 0;
        for(int i = 0; i < size; i++){
            if(str.charAt(i) == 'R'){
                b = true;
            }
            else if(str.charAt(i) == 'B' && b){
                c2 += 1;
            }
        }
        //bw.write("c1 : " + c1 + ", c2 : " + c2);
        return Math.min(c1, c2);

    }
}
