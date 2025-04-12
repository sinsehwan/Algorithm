/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int n;
	int sn;
	int[][] graph;
	int[][] students;
	
	int[][] vec = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));
		
		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		//Scanner sc = new Scanner(System.in);
		int T = 1;
		//T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			new Main().solve();
		}
		
		br.close();
		bw.close();
	}
	
	void solve() throws IOException{
		getInput();
		
		for(int i = 0; i < sn; i++) {
			findSeat(i);
		}
		
		
		
		int score = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				score += getScore(i, j);
			}
		}
		
		bw.write(score + "");
	}
	
	void getInput() throws IOException{
		n = Integer.parseInt(br.readLine());
		
		sn = n * n;
		
		graph = new int[n][n];
		students = new int[sn][5];
		
		for(int i = 0; i < sn; i++) {
			students[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt).toArray();
		}
	}
	
	void findSeat(int num) {
		int target = students[num][0];
		Set<Integer> fSet = new HashSet<>();
		for(int i = 1; i < 5; i++) {
			fSet.add(students[num][i]);
		}
		
		int BestFriendNum = -1;
		int BestLeftSeat = -1;
		int sy = -1, sx = -1;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(graph[i][j] != 0) {
					continue;
				}
				
				// 자리 주변 검색
				int fn = 0;
				int ls = 0;
				for(int[] v : vec) {
					int ny = i + v[0];
					int nx = j + v[1];
					if(isPromising(ny, nx)){
						if(graph[ny][nx] == 0) {
							ls += 1;
						}
						else if(fSet.contains(graph[ny][nx])) {
							fn += 1;
						}
					}
				}
				
				//조건 체크
				if(fn > BestFriendNum || (fn == BestFriendNum && ls > BestLeftSeat)) {
					BestFriendNum = fn;
					BestLeftSeat = ls;
					sy = i;
					sx = j;
				}
			}
		}
		graph[sy][sx] = target;
	}
	
	boolean isPromising(int y, int x) {
		return y >= 0 && y < n && x >= 0 && x < n;
	}
	
	int getPower(int e) {
		if(e == -1) {
			return 0;
		}
		else if(e == 0) {
			return 1;
		}
		else {
			return 10 * getPower(e - 1);
		}
	}
	
	int getScore(int y, int x) {
		int sIdx = -1;
		
		for(int i = 0; i < sn; i++) {
			if(graph[y][x] == students[i][0]) {
				sIdx = i;
			}
		}
		
		Set<Integer> fSet = new HashSet<>();
		for(int i = 1; i < 5; i++) {
			fSet.add(students[sIdx][i]);
		}
	
		int fNum = 0;
		
		for(int[] v : vec) {
			int ny = y + v[0];
			int nx = x + v[1];
					
			if(isPromising(ny, nx)) {
				if(fSet.contains(graph[ny][nx])) {
					fNum += 1;
				}
			}
		}
		return getPower(fNum - 1);
	}
	
}