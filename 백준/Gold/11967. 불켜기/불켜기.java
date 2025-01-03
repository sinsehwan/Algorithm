
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    static boolean[][] visited;
    static boolean[][] lighted;

    static Map<Tuple<Integer, Integer>, ArrayList<Tuple<Integer, Integer>>> graph = new HashMap<>();

    static LinkedList<Tuple<Integer, Integer>> queue = new LinkedList<>();

    static Set<Tuple<Integer, Integer>> possible = new HashSet<>();

    static int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int answer = 1;

    public static void main(String[] args) throws IOException {

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1][n + 1];
        lighted = new boolean[n + 1][n + 1];

        int x, y, a, b;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            ArrayList<Tuple<Integer, Integer>> li;
            if(!graph.containsKey(new Tuple<>(y, x))){
                graph.put(new Tuple<>(y, x), new ArrayList<>());
            }
            li = graph.get(new Tuple<>(y, x));
            li.add(new Tuple<>(b, a));
        }

        bfs(1, 1);

        /*for(boolean[] li : lighted){
            for(boolean item : li){
                bw.write(item + "");
            }
            bw.write("\n");
        }
        */

        bw.write(answer + "");

        br.close();
        bw.close();
    }

    public static void bfs(int y, int x){
        queue.offerLast(new Tuple<>(y, x));
        lighted[y][x] = true;
        visited[y][x] = true;

        while(!queue.isEmpty()){

            int qSize = queue.size();

            //System.out.println(qSize);
            for(int i = 0; i < qSize; i++){
                Tuple<Integer, Integer> q = queue.pollFirst();

                if(graph.containsKey(q)){
                    ArrayList<Tuple<Integer, Integer>> rooms = graph.get(q);
                    for(Tuple<Integer, Integer> r : rooms){
                        if(!lighted[r.getLeft()][r.getRight()]){
                            lighted[r.getLeft()][r.getRight()] = true;
                            answer += 1;
                        }
                    }
                }

                for(int[] v : vec){
                    int ny = q.getLeft() + v[0];
                    int nx = q.getRight() + v[1];

                    if(isPromising(ny, nx) && !visited[ny][nx]){
                        //System.out.println(ny + ", " + nx);
                        possible.add(new Tuple<>(ny, nx));
                    }
                }
            }

            List<Tuple<Integer, Integer>> toRemove = new ArrayList<>();

            for(Tuple<Integer, Integer> item : possible){
                //System.out.println("possible set 점검");
                int iy = item.getLeft();
                int ix = item.getRight();

                if(lighted[iy][ix]){
                    toRemove.add(new Tuple<>(iy, ix));
                    visited[iy][ix] = true;
                    queue.offerLast(item);
                }
            }

            possible.removeAll(toRemove);
        }
    }

    public static boolean isPromising(int y, int x){
        return y <= n && y > 0 && x <= n && x > 0;
    }
}

class Tuple<A, B> {
    private A left;
    private B right;

    public Tuple(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public A getLeft() {
        return left;
    }

    public void setLeft(A left) {
        this.left = left;
    }

    public B getRight() {
        return right;
    }

    public void setRight(B right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(left, tuple.left) && Objects.equals(right, tuple.right);
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, right);
    }
}
