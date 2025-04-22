import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    char root;
    Map<Character, Node> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Main().solve();

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();
        preOrder('A');
        bw.write("\n");
        inOrder('A');
        bw.write("\n");
        postOrder('A');
    }

    void getInput() throws IOException {
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            char c = st.nextToken().charAt(0);

            tree.put(a, new Node(b, c));
        }
    }


    void preOrder(char c) throws IOException {
        if(c != '.') {
            bw.write(c);
            preOrder(tree.get(c).left);
            preOrder(tree.get(c).right);
        }
    }

    void inOrder(char c) throws IOException {
        if(c != '.') {
            inOrder(tree.get(c).left);
            bw.write(c);
            inOrder(tree.get(c).right);
        }
    }

    void postOrder(char c) throws IOException {
        if(c != '.') {
            postOrder(tree.get(c).left);
            postOrder(tree.get(c).right);
            bw.write(c);
        }
    }
}

class Node{
    char left;
    char right;

    public Node(char left, char right){
        this.left = left;
        this.right = right;
    }
}
