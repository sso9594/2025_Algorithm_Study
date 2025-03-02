import java.util.ArrayDeque;
import java.util.Scanner;

public class Q암벽등반 {

	public static int[] horizX = {0, 0};
	public static int[] horizY = {1, -1};
	public static class Node{
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int ans = 0;
			
			int endX = -1;
			int endY = -1;
			int[][] map = new int[N][M];
			for(int i =0; i<N; i++) {
				for(int j =0; j<M; j++) {
					map[i][j] = sc.nextInt();
					
					if(map[i][j] == 3) {
						endX = i;
						endY = j;
					}
				}
			}
			int startX = N-1;
			int startY = 0;
			int level = 0;
			boolean[][] visited = new boolean[N][M];
			
			E : while(true) {
				// 단계별로 queue 초기화
				ArrayDeque<Node> queue = new ArrayDeque<>();
				queue.add(new Node(startX, startY));
				
				// 단계별로 visited 초기화
				visited = new boolean[N][M];
				visited[startX][startY] = true;
				
				while(!queue.isEmpty()) {
					Node now = queue.poll();
					if(now.x == endX && now.y == endY) {
						break E;
					}
					
					// 수평 움직임
					for(int h = 0; h<2; h++) {
						int hX = now.x + horizX[h];
						int hY = now.y + horizY[h];
						
						if(hY < 0 || hY >= M) continue;
						if(visited[hX][hY]) continue;
						if(map[hX][hY] == 0) continue;
						
						visited[hX][hY] = true;
						queue.add(new Node(hX, hY));
					}
					
					// 수직 움직임
					for(int v = now.x - level; v < now.x + level + 1; v++) {
						int vX = v;
						int vY = now.y;
											
						if(vX < 0 || vX >= N) continue;
						if(visited[vX][vY]) continue;
						if(map[vX][vY] == 0) continue;
						
						visited[vX][vY] = true;
						queue.add(new Node(vX, vY));
					}
				}
				
				level++;
			}
			sb.append("#"+ test_case + " " + level).append('\n');
		}
		System.out.print(sb.toString());
	}
}
