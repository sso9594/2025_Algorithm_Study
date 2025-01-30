import java.util.ArrayDeque;
import java.util.Scanner;

public class Q1012  {
	
	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {-1, 1, 0, 0};
	
	public static class Node{
		int x , y;
		
		public Node(int x, int y) {
			this.x =x;
			this.y =y;			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		
		for(int test_case = 0; test_case<tc; test_case++) {
			
			int N = sc.nextInt();			
			int M = sc.nextInt();			
			int K = sc.nextInt();			
			
			int[][] map = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			
			for(int i = 0; i<K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				map[x][y] = 1;
			}
			
			ArrayDeque<Node> queue= new ArrayDeque();
			int ans = 0;
			
			for(int i =0; i<N; i++) {
				for(int j =0; j<M; j++) {
				
					if(map[i][j] == 0) continue;
					if(visited[i][j]) continue;
					
					queue.add(new Node(i, j));
					visited[i][j] = true;
					ans++;
					while(!queue.isEmpty()) {
						Node now = queue.poll();
						
						for(int k =0; k<4; k++) {
							int nX = now.x + dX[k];
							int nY = now.y + dY[k];
							
							if(nX < 0 || nY<0 || nX >= N || nY >= M) continue;
							if(map[nX][nY] == 0) continue;
							
							if(!visited[nX][nY]) {
								visited[nX][nY] = true;
								queue.add(new Node(nX, nY));
							}
							
						}
						
					}
					
				}
			}
			
			sb.append(ans + "\n");
			
		}
		
		System.out.println(sb);
	}
}
