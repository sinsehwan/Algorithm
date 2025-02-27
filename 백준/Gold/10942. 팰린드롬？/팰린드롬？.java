import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr;
    static int[][] questions;

    static boolean[][] isPalindrome;


    public static void main(String[] args) throws IOException {
        getInput();

        calPalindrome();

        for(int[] q : questions){
            if(isPalindrome[q[0]][q[1]]){
                bw.write("1\n");
            }
            else{
                bw.write("0\n");
            }
        }
        /*
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                bw.write(i + " to " + j + ": " + isPalindrome[i][j] + "\n");
            }
        }
         */

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        m = Integer.parseInt(br.readLine());
        questions = new int[m][2];
        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            questions[i][0] = a;
            questions[i][1] = b;
        }
        isPalindrome = new boolean[n][n];
    }

    public static void calPalindrome(){
        calOdd();

        calEven();
    }

    public static void calOdd(){
        for(int i = 0; i < n; i++){
            isPalindrome[i][i] = true;
            int checkIdx = 1;
            while(i - checkIdx >= 0 && i + checkIdx < n){
                if(arr[i - checkIdx] == arr[i + checkIdx]){
                    isPalindrome[i - checkIdx][i + checkIdx] = true;
                    checkIdx += 1;
                }
                else{
                    break;
                }
            }
        }
    }

    public static void calEven(){
        for(int i = 0; i < n - 1; i++){
            if(arr[i] == arr[i + 1]){
                isPalindrome[i][i + 1] = true;
                isPalindrome[i + 1][i] = true;
                int checkIdx = 1;
                while(i - checkIdx >= 0 && i + 1 + checkIdx < n){
                    if(arr[i - checkIdx] == arr[i + 1 + checkIdx]){
                        isPalindrome[i - checkIdx][i + 1 + checkIdx] = true;
                        checkIdx += 1;
                    }
                    else{
                        break;
                    }
                }
            }
        }
    }
}
