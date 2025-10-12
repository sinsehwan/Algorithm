import java.util.*;

class Solution {
    
    int n;
    int[] diffs;
    int[] times;
    long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        this.n = diffs.length;
        
        return solve();
    }
    
    int solve() {
        return getLowerIdx();
    }
    
    int getLowerIdx() {
        int st = 1;
        int en = 100000;
        
        while(st < en) {
            int mid = st + (en - st) / 2;
            
            if (calTime(mid) > limit) {
                st = mid + 1;
            }
            else {
                en = mid;
            }
        }
        return st;
    }
    
    long calTime(int level) {
        long sum = 0L;
        
        sum += times[0];
        
        for (int i = 1; i < n; i++) {
            int sonhae = Math.max(diffs[i] - level, 0);
            
            sum += (long)sonhae * (times[i] + times[i - 1]) + times[i];
        }
        return sum;
    }
}