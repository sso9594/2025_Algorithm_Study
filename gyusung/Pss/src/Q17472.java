import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Q17472 {

	public static int N, M;
	public static int[][] map, adj;
	public static class Node{
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		for(int i =0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int cnt = 2;
		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				if(map[i][j] == 1) {
					numbering(i, j, cnt++);
				}
			}
		}
		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				if(map[i][j] > 1) {
					map[i][j] -= 1;
				}
			}
		}
		
		// 인접행렬을 만들자.
		adj = new int[cnt-2][cnt-2];
		for(int i =0; i<cnt-2; i++) {			
			Arrays.fill(adj[i], Integer.MAX_VALUE);
		}
		
		// 다리 건설 (모든 다리 만들어서 최소값만 저장)
		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				if(map[i][j] != 0) {
					find_bridge(i, j);
				}
			}
		}
		
		for(int i =0; i<cnt-2; i++) {
			for(int j =0; j<cnt-2; j++) {
				if(adj[i][j] >= Integer.MAX_VALUE) {
					adj[i][j] = 0;
				}
			}
		}
		
		//print(adj);
		
		// MST로 연결하기 ~~
		int nodes = cnt-2;
		boolean[] v = new boolean[nodes];
		int[] dist = new int[nodes];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		
		for(int i =0; i<nodes-1; i++) {
			int minV = -1;
			int minD = Integer.MAX_VALUE;
			
			for(int j = 0; j<nodes; j++) {
				if(minD > dist[j] && !v[j]) {
					// j번째 노드에 대해 방문한 적이 없고 dist[j]가 최소값이라면
					minD = dist[j];
					minV = j;
				}
			}
			
			if(minV == -1) break;	// 선택되지 않았다면 break;
			v[minV] = true;			// 선택된 항에 대해 방문처리 (MST에 포함되었음을 의미) 
			
			// minV = 기준노드로 가장 작은 값을 연결한다.
			for(int j =0; j<nodes; j++) {
				if(adj[minV][j] != 0 && !v[j] && adj[minV][j] < dist[j]) {
					// minV와 j가 이어져있고 && j번째 노드를 방문한 적이 없고 
					// && adj[][]가 dist[j]보다 작다면 ?? 
					dist[j] = adj[minV][j];
				}
			}
		}
		
		for(int i =0; i<nodes; i++) {
			if(dist[i] >= Integer.MAX_VALUE) {
				System.out.println(-1);
				System.exit(0);
			}
		}	
		
		System.out.println(Arrays.stream(dist).sum());
		
	}
	
	static int[] dX = {0, 0, -1, 1};
	static int[] dY = {1, -1, 0, 0};

	private static void find_bridge(int r, int c) {
		// TODO Auto-generated method stub
		int s = map[r][c]-1;
		int e = -1;
		
		int bridge_len = 0;
		
		for(int dir=0; dir<4; dir++) {
			int nX = r + dX[dir];
			int nY = c + dY[dir];
			bridge_len = 0;
			
			while(nX >= 0 && nY >= 0 && nX < N && nY < M && map[nX][nY] == 0) {	
				nX += dX[dir];
				nY += dY[dir];
				bridge_len++;
				
				if(nX >= 0 && nY >= 0 && nX < N && nY < M && map[nX][nY] > 0) {		
					
					e = map[nX][nY] - 1;
					if(adj[s][e] > bridge_len && bridge_len >= 2) {
					adj[s][e] = Math.min(adj[s][e], bridge_len);
					adj[e][s] = Math.min(adj[e][s], bridge_len);
					}
				}
			}		
		}
		
	}



	private static void numbering(int r, int c, int num) {
		// TODO Auto-generated method stub

		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(new Node(r, c));
		map[r][c] = num;

		while(!queue.isEmpty()) {
			Node now = queue.poll();

			for(int dir = 0; dir<4; dir++) {
				int nR = now.r + dX[dir];
				int nC = now.c + dY[dir];

				if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
				if(map[nR][nC] == 1) {
					map[nR][nC] = num;
					queue.add(new Node(nR, nC));
				}
			}

		}

	}
	
	public static void print(int[][] map) {
		for(int i =0; i<map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
