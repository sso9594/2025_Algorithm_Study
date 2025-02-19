import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q17135 {

	public static int[] selected;
	public static boolean[] v;
	public static int N, M, d, caught;
	public static int[][] map;
	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {-1, 1, 0, 0};
	public static class Node{
		int x, y, dist;
		
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		d = sc.nextInt();
		
		map = new int[N+1][M];
		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		selected = new int[3];
		Arrays.fill(selected, -1);
		v = new boolean[M];
		caught = Integer.MIN_VALUE;
		
		//조합 구하기
		dfs(0, 0);
		System.out.println(caught);
	}

	private static void dfs(int now, int sum) {
		// TODO Auto-generated method stub
		if(sum >= 3) {
			start(selected, deepCopy(map));
			return;
		}
		
		for(int i = now; i<M; i++) {
			if(v[i]) continue;
			
			v[i] = true;
			selected[sum] = i;
			
			dfs(i, sum+1);

			v[i] = false;
			selected[sum] = -1;
		}
		
	}

	private static void start(int[] selected, int[][] map) {
		// TODO Auto-generated method stub
		int now_caught = 0;
		
		//모든 턴 진행
		for(int t = 0; t<N; t++) {
			// 궁수마다 가장 가깝고, 왼쪽인 적 찾아서 -1
			Node[] targets = new Node[3];
			for(int k = 0; k<3; k++) {
				boolean[][] visited = new boolean[N+1][M];
				
				ArrayDeque<Node> queue = new ArrayDeque<>();
				PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
					if(o1.dist == o2.dist) return Integer.compare(o1.y, o2.y);
					else return Integer.compare(o1.dist, o2.dist);
				});
				queue.add(new Node(N, selected[k], 0));

				while(!queue.isEmpty()) {
					Node now = queue.poll();
					visited[now.x][now.y] = true;
					
					for(int dir = 0; dir<4; dir++) {
						int nextX = now.x + dX[dir];
						int nextY = now.y + dY[dir];
						
						if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
						if(visited[nextX][nextY]) continue;
						
						if(now.dist + 1 <= d) {	// 가용한 거리라면
							queue.add(new Node(nextX, nextY, now.dist + 1));
							if(map[nextX][nextY] == 1) {
								pq.add(new Node(nextX, nextY, now.dist + 1));
							}
						}
					}
				}
				if(!pq.isEmpty()) {
					targets[k] = pq.poll();
				}
			}

			for(int i =0; i<3; i++) {
				if(targets[i] != null) {
					if(map[targets[i].x][targets[i].y] == 1) {
						map[targets[i].x][targets[i].y] = 0;					
						now_caught++;
					}
				}
			}
			
			//아래로 한칸
			for(int i = N-1; i>0; i--) {
				for(int j=0; j<M; j++) {
					map[i][j] = map[i-1][j];
				}
			}
			
			//위에 칸 삭제
			for(int i =0; i<=t; i++) {
				for(int j=0; j<M; j++) {
					map[i][j] = 0;
				}
			}	
		}
		
		caught = Math.max(caught, now_caught);	
	}
	

	private static int[][] deepCopy(int[][] map2) {
		// TODO Auto-generated method stub
		
		int[][] n_map = new int[N+1][M];
		for(int i = 0; i<N; i++) {
			for(int j =0; j<M; j++) {
				n_map[i][j] = map2[i][j];
			}
		}
		
		return n_map ;
	}
}