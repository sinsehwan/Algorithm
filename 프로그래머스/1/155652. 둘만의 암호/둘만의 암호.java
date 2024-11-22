import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s, String skip, int index) {
        char[] answer = new char[s.length()];
        
        char[] abc = new char[26 - skip.length()];
        int abcIndex = 0;
        for(char c = 'a'; c <= 'z'; c++) {
            if(skip.indexOf(c) == -1){
                abc[abcIndex] = c;
                abcIndex += 1;
            }
        }
        //
        //for (char item : abc){
        //    System.out.print(item);
        //}
        //System.out.println();
        
        for(int i = 0; i < s.length(); i++){
            int pIndex = findIndex(abc, 26 - skip.length(), s.charAt(i));
            answer[i] = abc[(pIndex + index) % (26 - skip.length())];
            //System.out.println(i + " " + answer[i] + " " + pIndex);
        }
        return new String(answer);
    }
    
    public int findIndex(char[] arr, int arrSize, char value){
        int result = -1;
        for(int i = 0; i < arrSize; i++){
            if (arr[i] == value){
                result = i;
                break;
            }
        }
        return result;
    }
}