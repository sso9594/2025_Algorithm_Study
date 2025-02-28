import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q16236 {

	public static class Node{
		int x, y, dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	public static int[] dX = {-1, 0, 1, 0};
	public static int[] dY = {0, 1, 0, -1};

	public static PriorityQueue<Node> pq;
	public static ArrayDeque<Node> queue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N][N];

		int[] fishes = new int[7];
		int shark_level = 2;
		int now_ate = 0;
		int[] shark_loca = new int[2];
		int sec = 0;
		
		for(int i =0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();

				if(map[i][j] > 0 && map[i][j] < 7) {
					fishes[map[i][j]]++;
				}else if(map[i][j] == 9) {
					shark_loca[0] = i;
					shark_loca[1] = j;
				}
			}
		}

		while(true) {
			
			queue = new ArrayDeque<>();									// queue 초기화
			queue.add(new Node(shark_loca[0], shark_loca[1], 0));		// 상어의 현재 위치 초기화
			map[shark_loca[0]][shark_loca[1]] = 0;
			boolean[][] v = new boolean[N][N];							// 방문 배열 초기화
			
			pq = new PriorityQueue <>((o1, o2) -> {						// pq 초기화
				if(o1.dist != o2.dist) {	
					return Integer.compare(o1.dist, o2.dist);
				}else if(o1.x != o2.x) {
					return Integer.compare(o1.x, o2.x);
				}else return Integer.compare(o1.y, o2.y);
			});
			
			
			while(!queue.isEmpty()) {
				Node now = queue.poll();

				for(int dir = 0; dir < 4; dir++) {						// 와구와구 쩝쩝

					int nX = now.x + dX[dir] ;
					int nY = now.y + dY[dir] ;


					if(nX < 0 || nX >= N || nY < 0 || nY >= N ) continue;
					if(map[nX][nY] > shark_level) continue;
					if(v[nX][nY]) continue;

					v[nX][nY] = true;
					queue.add(new Node(nX, nY, now.dist + 1));

					if(map[nX][nY] > 0 && map[nX][nY] < shark_level) pq.add(new Node(nX, nY, now.dist + 1));
				}
			}

			// 지금 먹을 수 있는 모든 물고기 탐색 완료 ... 하나 빼서 먹은 표시 ㄱㄱ
			if(!pq.isEmpty()) {
				Node to_eat = pq.poll();

				//System.out.println(to_eat.x + " " + to_eat.y + " lv: "+ shark_level + " t :" + map[to_eat.x][to_eat.y]);
				
				//먹은 곳 처리
				map[to_eat.x][to_eat.y] = 0;
				// 상어 level up 확인
				now_ate++;
				if(now_ate == shark_level) {
					shark_level++;
					now_ate = 0;
				}
				
				sec += to_eat.dist;

				// 다음 시작할 초기 값 설정
				shark_loca[0] = to_eat.x;
				shark_loca[1] = to_eat.y;
			}else break;
		}

		System.out.println(sec);
		
	}


}
