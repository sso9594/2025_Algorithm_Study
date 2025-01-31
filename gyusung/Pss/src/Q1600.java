import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Q1600 {
	
	public static int[] dX = {0,0,-1,1};
	public static int[] dY = {-1,1,0,0};
	public static int[] horse_dX = {1, 1, -1, -1, 2, 2, -2, -2};
	public static int[] horse_dY = {2, -2, 2, -2, 1, -1, 1, -1};

	
	
	public static class Node{
		int x, y, dist, jumped;
		
		public Node(int x, int y, int dist, int jumped) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.jumped = jumped;			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		int[][] map = new int[N][M];
		for(int i = 0; i<N; i++) {
			for(int j= 0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		boolean[][][] visited = new boolean[N][M][k+1];
		int[][] min_dist = new int[N][M];
		
		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			//System.out.println(now.x + " " + now.y + " : " + now.jumped);
			
			if(min_dist[now.x][now.y] == 0) {
				min_dist[now.x][now.y] = now.dist;
//			}else if(now.dist > min_dist[now.x][now.y]) {
//				min_dist[now.x][now.y] = now.dist;
//			}
			}
			if(now.x == N-1 && now.y == M-1) {
				//System.out.println(now.x + " " + now.y);
				System.out.println(now.dist);
				System.exit(0);
			}
			
			// 일반 움직임
			for(int i =0; i<4; i++) {
				int nX = now.x + dX[i];
				int nY = now.y + dY[i];
				
				if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
				if(map[nX][nY] == 1) continue;
				if(visited[nX][nY][now.jumped]) continue;
				// 값 계산 (최소 아니면 안가.) --> 할 필요 없음
				
				// visited 표시
				visited[nX][nY][now.jumped] = true;
				// queue.add();
				queue.add(new Node(nX, nY, now.dist+1, now.jumped));
			}
			
			// 말 움직임
			if(now.jumped < k) {
				for(int i=0; i<8; i++) {
					int nX = now.x + horse_dX[i];
					int nY = now.y + horse_dY[i];
					
					if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
					if(map[nX][nY] == 1) continue;
					if(visited[nX][nY][now.jumped+1]) continue;
					// 값 계산 (최소 아니면 안가.) --> 할 필요 없음
					
					// visited 표시
					visited[nX][nY][now.jumped+1] = true;
					// queue.add();
					queue.add(new Node(nX, nY, now.dist+1, now.jumped+1));
				}
			}
			
			//System.out.println(Arrays.deepToString(min_dist));
			//System.out.println(Arrays.deepToString(visited));
		}
		System.out.println("-1"); 
	}
}
