import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] initArr;
    static int[] answerArr;

    public static void main(String[] args) throws IOException {
        getInput();

        int r1 = calCase1();
        int r2 = calCase2();

        if(r1 == -1 && r2 == -1){
            bw.write("-1");
        }
        else if(r1 == -1){
            bw.write(r2 + "");
        }
        else if(r2 == -1){
            bw.write(r1 + "");
        }
        else{
            bw.write(Math.min(r1, r2) + "");
        }

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        initArr = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt).toArray();

        answerArr = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt).toArray();
    }

    public static int calCase1(){
        // 0번 스위치는 항상 건드리지 않음.
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = initArr[i];
        }

        int count = 0;
        for(int i = 0; i < n - 2; i++){
            if(a[i] != answerArr[i]){
                //i + 1번 스위치 켜기
                a[i] = (a[i] + 1) % 2;
                a[i + 1] = (a[i + 1] + 1) % 2;
                a[i + 2] = (a[i + 2] + 1) % 2;
                count += 1;
            }
        }
        if(a[n - 2] != answerArr[n - 2]){
            a[n - 2] = (a[n - 2] + 1) % 2;
            a[n - 1] = (a[n - 1] + 1) % 2;
            count += 1;
        }

        if(a[n - 1] != answerArr[n - 1]){
            return -1;
        }
        else{
            return count;
        }
    }

    public static int calCase2(){
        // 0번 스위치를 기본으로 켜고 시작하는 경우
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = initArr[i];
        }

        int count = 1;
        a[0] = (a[0] + 1) % 2;
        a[1] = (a[1] + 1) % 2;

        for(int i = 0; i < n - 2; i++){
            if(a[i] != answerArr[i]){
                //i + 1번 스위치 켜기
                a[i] = (a[i] + 1) % 2;
                a[i + 1] = (a[i + 1] + 1) % 2;
                a[i + 2] = (a[i + 2] + 1) % 2;
                count += 1;
            }
        }
        if(a[n - 2] != answerArr[n - 2]){
            a[n - 2] = (a[n - 2] + 1) % 2;
            a[n - 1] = (a[n - 1] + 1) % 2;
            count += 1;
        }

        if(a[n - 1] != answerArr[n - 1]){
            return -1;
        }
        else{
            return count;
        }
    }
}
