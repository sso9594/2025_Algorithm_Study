import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Q7569 {

	public static int N, M, H;
	public static int[] dX = {0, 0, 0, 0, -1 , 1}; 
	public static int[] dY = {0, 0, 1, -1, 0 , 0}; 
	public static int[] dZ = {1, -1, 0, 0, 0 , 0}; 
	public static int[][][] visited;

	public static class Node{
		int x, y, z, dist;
		public Node(int x, int y, int z, int dist) {
			this.x=x;
			this.y=y;
			this.z=z;
			this.dist=dist;			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();

		int[][][] map = new int[H][N][M];
		for(int k =0 ;k<H; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[k][i][j] = sc.nextInt();
				}
			}
		}

		ArrayDeque<Node> queue = new ArrayDeque<>();
		visited = new int[H][N][M];

		for(int k =0 ;k<H; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[k][i][j] == 1) { 
						queue.add(new Node(i, j, k, 1));
						visited[k][i][j] = 1;
					}
				}
			}
		}

		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			for(int i =0; i<6; i++) {
				
				int nX = now.x + dX[i];
				int nY = now.y + dY[i];
				int nZ = now.z + dZ[i];
			
				if(nX < 0 || nX >= N || nY < 0 || nY >= M || nZ <0 || nZ >=H) continue;
				if(map[nZ][nX][nY] == -1 || map[nZ][nX][nY] == 1) continue;
				if(visited[nZ][nX][nY] != 0) continue;
				
				if(visited[nZ][nX][nY] == 0) {
					visited[nZ][nX][nY] = now.dist + 1;
					queue.add(new Node(nX, nY, nZ, now.dist + 1));
				}
				
			}
			
		}
		
		int ans = 0;
		for(int k =0 ;k<H; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[k][i][j] == 0 && map[k][i][j] == 0) {
						System.out.println("-1");
						return;
						}
					if(visited[k][i][j] > ans) ans = visited[k][i][j];
				}
			}
		}
		
		System.out.println(ans-1);
		
	}

}
