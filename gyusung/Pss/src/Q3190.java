import java.util.ArrayList;
import java.util.Scanner;

public class Q3190 {
	
	public static int[] dX = {0, 1, 0, -1};
	public static int[] dY = {1, 0, -1, 0};
	public static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] map = new int[N][N];
		
		int K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			int x = sc.nextInt()-1;
			int y = sc.nextInt()-1;
		
			map[x][y] = 2;
		}
		
		int L = sc.nextInt();
		String[] change_dir = new String[10_001];
		for (int i = 0; i < L; i++) {
			int sec = sc.nextInt();
			change_dir[sec] = sc.next(); 
		}
		
		int nowX = 0;
		int nowY = 0;
		int nowD = 0;
		int cnt = 0;
		
		ArrayList<Node> snake = new ArrayList<>();
		snake.add(new Node(0, 0));
		
		while(true) {
			// 시간
			cnt++;
			
			// 뱀 이동
			nowX += dX[nowD];
			nowY += dY[nowD];
			
			// 방향전환
			if(change_dir[cnt] != null) {
				if(change_dir[cnt].charAt(0) == 'L') {	// 좌로 90
					nowD -= 1;
					nowD = (nowD < 0)? 3 : nowD;
				}else if(change_dir[cnt].charAt(0) == 'D') {	// 우로 90
					nowD += 1;
					nowD %= 4; 
				}
			}
			
			// 죽었는지 확인
			if(meetSnake(nowX, nowY, snake)) break;
			if(rangeOut(nowX, nowY)) break;
			
			snake.add(new Node(nowX, nowY));	// snake =  0 : 꼬리 <-> size : 머리
			
			// 사과 확인				
			if(map[nowX][nowY] == 2) {	// 있으면
				map[nowX][nowY] = 0;	// 사과 먹고 가마이써
			}else {						// 없으면
										// 꼬리 쪽 방 빼
				snake.remove(0);
			}
		}
		
		System.out.println(cnt);
		
	}

	private static boolean rangeOut(int nX, int nY) {
		// TODO Auto-generated method stub
		if(nX < 0 || nX >= N || nY < 0 || nY >= N) {
			return true;
		}
		return false;
	}

	private static boolean meetSnake(int nX, int nY, ArrayList<Node> snake) {
		// TODO Auto-generated method stub
		for(int i = 0; i<snake.size(); i++) {
			Node now = snake.get(i);			
			if(now.x == nX && now.y == nY) {
				return true;
			}
		}
				
		return false;
	}
}
