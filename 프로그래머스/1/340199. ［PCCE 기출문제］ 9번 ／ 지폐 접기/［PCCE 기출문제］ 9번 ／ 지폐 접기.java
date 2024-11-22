import java.util.*;
import java.io.*;

class Solution {
    int walletX;
    int walletY;
    int bX;
    int bY;
    
    int count = 0;
    public int solution(int[] wallet, int[] bill) {
        if(wallet[0] > wallet[1]){
            walletX = wallet[0];
            walletY = wallet[1];
        }
        else{
            walletX = wallet[1];
            walletY = wallet[0];
        }
        
        Arrays.sort(bill);
        bX = bill[1];
        bY = bill[0];
        
        doHalf();
        
        return count;
    }
    
    public void doHalf() {
        if (walletX >= bX && walletY >= bY){
            return;
        }
        else{
            count += 1;
            int temp = bX / 2;
            if (temp >= bY){
                bX = temp;
            }
            else{
                bX = bY;
                bY = temp;
            }
            doHalf();
        }
    } 
}