import java.io.*;
import java.util.*;

/*
logList : 1번 2번 쿼리만 stack으로 저장
3 j 수행 시 개수만큼 pop해서 반대로 수행.
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Log> logList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            if(command == 1){
                int value = Integer.parseInt(st.nextToken());
                stack.offerLast(value);
                logList.offerLast(new Log(1, value));
            }
            else if(command == 2){
                int value = stack.pollLast();
                logList.offerLast(new Log(2, value));
            }
            else if(command == 3){
                int count = Integer.parseInt(st.nextToken());

                for(int j = 0; j < count; j++){
                    Log log = logList.pollLast();
                    if(log.command == 1){
                        stack.pollLast();
                    }
                    else{
                        stack.offerLast(log.value);
                    }
                }
            }
            else if(command == 4){
                int len = stack.size();
                bw.write(len + "\n");
            }
            else { // 5
                if(stack.isEmpty()){
                    bw.write("-1\n");
                }
                else{
                    int last = stack.getLast();
                    bw.write(last + "\n");
                }
            }
        }
    }
}

class Log{
    int command;
    int value;

    Log(int command, int value){
        this.command = command;
        this.value = value;
    }
}
