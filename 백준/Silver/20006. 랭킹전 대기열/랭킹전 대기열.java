import java.io.*;
import java.util.*;

// 방을 ArrayList로 관리.
// Room -> player[]
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int p, m;

    Player[] playerLi;
    ArrayList<Room> rooms = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        for(Player pl : playerLi){
            enter(pl);
        }

        for(Room r : rooms){
            if(r.isFull()){
                bw.write("Started!\n");
            }
            else{
                bw.write("Waiting!\n");
            }

            while(!r.players.isEmpty()){
                Player pl = r.players.poll();
                bw.write(pl.level + " " + pl.id + "\n");
            }
        }
    }

    void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        playerLi = new Player[p];
        for(int i = 0; i < p; i++){
            st = new StringTokenizer(br.readLine());

            int level = Integer.parseInt(st.nextToken());
            String id = st.nextToken();
            playerLi[i] = new Player(level, id);
        }
    }

    void enter(Player pl){
        for(Room r : rooms){
            if(!r.isFull() && Math.abs(r.initLevel - pl.level) <= 10){
                r.addPlayer(pl);
                return;
            }
        }

        rooms.add(new Room(pl, m));
    }
}

class Player implements Comparable<Player> {
    int level;
    String id;

    Player(int level, String id){
        this.level = level;
        this.id = id;
    }

    @Override
    public int compareTo(Player other){
        return id.compareTo(other.id);
    }
}

class Room{
    PriorityQueue<Player> players = new PriorityQueue<>();
    int pNum;
    int initLevel;
    int size;

    Room(Player p, int size){
        players.add(p);
        pNum = 1;
        initLevel = p.level;
        this.size = size;
    }

    void addPlayer(Player p){
        if(isFull()) return;
        players.add(p);
        pNum += 1;
    }

    boolean isFull(){
        return pNum == size;
    }
}