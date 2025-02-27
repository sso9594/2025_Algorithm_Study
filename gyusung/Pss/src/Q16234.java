import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Q16234 {
	
	public static int N, L, R, n_cnt;
	public static int[][] union, map;
	public static class Node{
		int x, y, popul;
		
		public Node(int x, int y, int popul) {
			this.x = x;
			this.y = y;
			this.popul = popul;
		}
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		 N = sc.nextInt();
		 L = sc.nextInt();
		 R = sc.nextInt();
		
		map = new int[N][N];
		for(int i = 0; i<N; i++) {
			for(int j =0;j<N;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int ans = 0;
		
		while(true) {
			union = new int[N][N];
			bfs();
			distribute();
			
			boolean flag = false;
			for(int i =0; i<N; i++) {
				for(int j =0; j<N; j++) {
					if(union[i][j] != 0) flag = true;
				}
			}
			if(!flag) break;
			
			ans ++;
		}
		
		System.out.println(ans);
	}

	private static void distribute() {
		// TODO Auto-generated method stub
		
		// 전체 연합 개수에 대해
		for(int c = 1; c<n_cnt; c++) {
		
			int sum = 0;
			int num = 0;
			
			//일단 합을 구해
			for(int i = 0; i<N; i++) {
				for(int j =0; j<N;j++) {
					if(union[i][j] == c) {
						sum += map[i][j];
						num++;
					}
				}
			}

			// 합 / 나라 수를 대입해
			for(int i = 0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(union[i][j] == c) {
						map[i][j] = sum / num;
					}
				}
			}
		}
	}

	private static int[] dX = {0,0,-1,1};
	private static int[] dY = {1,-1,0,0};
	
	private static void bfs() {
		// TODO Auto-generated method stub
		
		int cnt = 1;
		
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				
				if(union[i][j] != 0) continue;
				
				boolean flag = false;
				for(int dir = 0; dir<4; dir++) {
					int nX = i + dX[dir];
					int nY = j + dY[dir];
					
					if(nX < 0 || nX >= N || nY < 0 || nY >= N) continue;
					if(Math.abs(map[nX][nY] - map[i][j]) >= L && Math.abs(map[nX][nY] - map[i][j]) <= R) flag = true;
				}
				if(!flag) continue;
				
				ArrayDeque<Node> queue = new ArrayDeque<>();
				queue.add(new Node(i, j, map[i][j]));
				
				while(!queue.isEmpty()) {
					
					Node now = queue.poll();
					
					for(int dir = 0; dir<4; dir++) {
						
						int nX = now.x + dX[dir];
						int nY = now.y + dY[dir];
						
						if(nX < 0 || nX >= N || nY < 0 || nY >= N) continue;
						if(union[nX][nY] != 0) continue;
						if(Math.abs(map[nX][nY] - map[now.x][now.y]) >= L && Math.abs(map[nX][nY] - map[now.x][now.y]) <= R) {
							union[nX][nY] = cnt;
							queue.add(new Node(nX, nY, map[nX][nY]));	
						}	
					}
				}
				// 한번 했으면
				cnt++;
				
			}
		}
		
		n_cnt = cnt;
		
	}

}
