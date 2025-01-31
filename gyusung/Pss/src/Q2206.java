import java.util.ArrayDeque;
import java.util.Scanner;

public class Q2206 {

	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {1, -1, 0, 0};
	
	public static class Node{
		int x, y, dist;
		boolean used;
		
		public Node(int x, int y, boolean used, int dist) {
			this.x = x;
			this.y = y;
			this.used = used;
			this.dist = dist;			
		}	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		String[] strs = new String[N];
		for(int i =0; i<N; i++) {
			strs[i] = sc.next();
		}
		
		int[][] map = new int[N][M];
		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				map[i][j] = strs[i].charAt(j) - '0';
			}
		}
		
		boolean[][][] visited = new boolean[N][M][2];

		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, false, 1));
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			if(now.x == N-1 && now.y == M-1) {
				System.out.println(now.dist);
				System.exit(0);
			}
			
			for(int i =0; i<4; i++) {
				int nX = now.x + dX[i];
				int nY = now.y + dY[i];
				
				if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
				if(now.used == true && map[nX][nY] == 1) continue;
				
				// 방문처리
				if(now.used) {	// 이미 부순 경우
					if(visited[nX][nY][1]) continue;
					else visited[nX][nY][1] = true;
				}else {
					if(visited[nX][nY][0]) continue;
					else visited[nX][nY][0] = true;
				}
				
				if(map[nX][nY] == 1 || now.used == true) {
					queue.add(new Node(nX, nY, true, now.dist + 1));
				}else {
					queue.add(new Node(nX, nY, false, now.dist + 1));
				}
			}
			
		}
		
		System.out.println("-1");
	}

}
