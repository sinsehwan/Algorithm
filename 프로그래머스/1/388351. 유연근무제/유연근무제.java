import java.io.*;

class Solution {
    int[] schedules;
    int[][] timelogs;
    int startday;
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        getInput(schedules, timelogs, startday);
        int answer = solve();
        return answer;
    }
    
    public void getInput(int[] schedules, int[][] timelogs, int startday) {
        this.schedules = schedules;
        this.timelogs = timelogs;
        this.startday = startday;
    }
    
    int solve() {
        int answer = 0;
        
        
        for (int i = 0; i < schedules.length; i++) {
            boolean out = false;
            
            int minute = schedules[i] % 100;
            int timeout = 0;
            if (minute + 10 >= 60) {
                timeout = schedules[i] + 50;
            }
            else {
                timeout = schedules[i] + 10;
            }
            
            
            for (int j = 0; j < 7; j++) {
                int day = (j + startday - 1) % 7 + 1;
                if (day >= 6) {
                    continue;
                }
                if (timeout < timelogs[i][j]) {
                    out = true;
                    break;
                }
            }
            
            if (!out) {
                answer += 1;
            }
        }
        return answer;
    }
}