import java.io.*;
import java.util.*;

// 가격은 100 곱해서 int로 만들기

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;

    ArrayList<Candy> candies = new ArrayList<>();

    int[] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = priceToInt(Double.parseDouble(st.nextToken()));

            if(n == 0 && m == 0) break;
            new Main().solve();
        }

        br.close();
        bw.close();
    }

    void solve() throws IOException {
        getInput();

        dp = new int[m + 1];

        for(int i = 0; i < candies.size(); i++){
            Candy c = candies.get(i);
            for (int j = c.price; j <= m; j++){
                dp[j] = Math.max(dp[j - c.price] + c.cal, dp[j]);
            }
        }

        bw.write(dp[m] + "\n");
    }

    void getInput() throws IOException {

        int cal, price;
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            cal = Integer.parseInt(st.nextToken());
            price = priceToInt(Double.parseDouble(st.nextToken()));

            candies.add(new Candy(cal, price));
        }
    }

    static int priceToInt(double price){
        return (int) (price * 100 + 0.5); // rounding error 방지
    }
}

class Candy {
    int cal;
    int price;

    Candy(int cal, int price){
        this.cal = cal;
        this.price = price;
    }
}
