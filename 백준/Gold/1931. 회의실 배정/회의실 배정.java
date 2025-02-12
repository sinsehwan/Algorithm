import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;

    static ArrayList<Conference> cfInfo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        bw.write(getAnswer() + "");
        br.close();
        bw.close();
    }

    public static void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            cfInfo.add(new Conference(startTime, end));
        }

        /**
         * 끝나는 시간 순으로 정렬.
         * 끝나는 시간이 같다면 시작시간이 빠른 게 앞으로 옴.
         */
        Collections.sort(cfInfo, (a, b) -> {
            int endTimeA = a.getEnd();
            int endTimeB = b.getEnd();

            if(endTimeA < endTimeB){
                return -1;
            }
            else if(endTimeA > endTimeB){
                return 1;
            }
            else{
                if(a.getStart() < b.getStart()){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        });
    }

    public static int getAnswer() {
        int count = 0;
        int preEnd = 0;

        for(int i = 0; i < cfInfo.size(); i++){
            int st = cfInfo.get(i).getStart();
            int en = cfInfo.get(i).getEnd();

            if(st >= preEnd){
                //System.out.println("st : " + st + ", en : " + en);
                count += 1;
                preEnd = en;
            }
        }

        return count;
    }
}

class Conference{
    private int start;
    private int end;

    public Conference(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}