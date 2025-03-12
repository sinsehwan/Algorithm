import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        
        Arrays.sort(mats);
        int n = park.length;
        int m = park[0].length;
        
        int[][] graph = new int[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(park[i][j].length() == 2){
                    graph[i][j] = 0;
                }
                else{
                    graph[i][j] = 1;
                }
            }
        }
        
        int[][] dp = new int[n][m];
        
        int bestSize = 0;
        for(int i = 0; i < n; i++){
            if(graph[i][0] == 0){
                dp[i][0] = 1;
            }
        }
        for(int j = 0; j < m; j++){
            if(graph[0][j] == 0){
                dp[0][j] = 1;
            }
        }
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(graph[i][j] == 0)
                {
                    if(graph[i - 1][j - 1] == 0 && graph[i - 1][j] == 0 && graph[i][j - 1] == 0){
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    }   
                    else{
                        dp[i][j] = 1;
                    }
                    if(dp[i][j] > bestSize){
                        bestSize = dp[i][j];
                    }
                }
            }
        }
        
        
        for(int i = mats.length - 1; i >= 0; i--){
            if(mats[i] <= bestSize){
                return mats[i];
            }
        }
        
        
        return answer;
    }
}