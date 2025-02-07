import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, w, l;

    static LinkedList<Truck> bridge = new LinkedList<>();
    static LinkedList<Integer> truckList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();

        int time = 0;
        while(!truckList.isEmpty() || !bridge.isEmpty()){
            time += 1;
            checkFirst(time);
            if(!truckList.isEmpty() && calTotalWeight() + truckList.getFirst() <= l){
                go(time);
            }
        }

        bw.write(time + "");

        br.close();
        bw.close();
    }

    public static void getInput() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            truckList.offerLast(Integer.parseInt(st.nextToken()));
        }
    }

    public static int calTotalWeight(){
        int total = 0;

        for(Truck t : bridge){
            total += t.getWeight();
        }
        return total;
    }

    public static void checkFirst(int time){
        if(!bridge.isEmpty() && time - bridge.getFirst().getTime() >= w){
            bridge.pollFirst();
        }
    }

    public static void go(int time){
        if(!truckList.isEmpty()){
            bridge.offerLast(new Truck(truckList.pollFirst(), time));
        }
    }
}

class Truck{
    private int weight;
    private int time;

    public Truck(int weight, int time){
        this.weight = weight;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getWeight() {
        return weight;
    }
}