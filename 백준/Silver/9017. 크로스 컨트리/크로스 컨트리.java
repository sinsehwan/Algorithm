import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int[] initArr;
    int[] arr;
    Map<Integer, Integer> memberNum = new HashMap<>();

    int[] teamCount;
    int[] teamScore;

    int[] team5;

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            getInput();

            teamScore = new int[201];
            team5 = new int[201];
            teamCount = new int[201];

            for(int j = 1; j < arr.length; j++){
                teamCount[arr[j]] += 1;
                if(teamCount[arr[j]] <= 4){
                    teamScore[arr[j]] += j;
                }
                else if(teamCount[arr[j]] == 5){
                    team5[arr[j]] = j;
                }
            }

            int best = Integer.MAX_VALUE;
            int best5 = 0;
            int bestIdx = -1;

            for(int j = 1; j <= 200; j++){
                if(teamScore[j] != 0){
                    if(teamScore[j] < best){
                        best5 = team5[j];
                        best = teamScore[j];
                        bestIdx = j;
                    }
                    else if(teamScore[j] == best && team5[j] < best5){
                        best5 = team5[j];
                        best = teamScore[j];
                        bestIdx = j;
                    }
                }
            }

            bw.write(bestIdx + "\n");
        }
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());
        memberNum = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        initArr = new int[n + 1];

        for(int i = 1; i <= n; i++){
            initArr[i] = Integer.parseInt(st.nextToken());
            int value = initArr[i];
            memberNum.put(value, memberNum.getOrDefault(value, 0) + 1);
        }

        int validNum = 0;
        for(Map.Entry<Integer, Integer> n : memberNum.entrySet()){
            if(n.getValue() == 6) {
                validNum += n.getValue();
            }
        }

        arr = new int[validNum + 1];

        int idx = 1;
        for(int i = 1; i <= n; i++){
            if(memberNum.containsKey(initArr[i]) && memberNum.get(initArr[i]) == 6){
                arr[idx] = initArr[i];
                idx += 1;
            }
        }
    }
}
