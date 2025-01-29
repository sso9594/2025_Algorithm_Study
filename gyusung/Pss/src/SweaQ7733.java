import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayDeque;

class SweaQ7733
{
	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {-1, 1, 0, 0};
	public static class Node{
		int x; 
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String args[]) throws Exception{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++){
			int N = sc.nextInt();
			int max_day = 0;

			int[][] map = new int[N][N];
			for(int i =0; i<N; i++) {
				for(int j =0; j<N; j++) {
					map[i][j] = sc.nextInt();
					if(max_day < map[i][j]) max_day = map[i][j];
				}
			}

			ArrayDeque<Node>queue= new ArrayDeque<>();
			int max = 0;
			
			//bfs
			for(int day = 0; day <= max_day; day++) {

				boolean[][] visited = new boolean[N][N];
				int cnt = 0;

				for(int i = 0; i<N; i++) {
					for(int j = 0; j<N; j++) {

						if(!visited[i][j] && map[i][j] > day) {

							queue.add(new Node(i, j));
							visited[i][j] = true;
							cnt++;

							while(!queue.isEmpty()) {
								Node now = queue.poll();

								for(int r = 0; r<4; r++) {
									int nX = now.x + dX[r];
									int nY = now.y + dY[r];

									if(nX < 0 || nY <0|| nX >= N || nY >= N) continue;
									if(map[nX][nY] <= day ) continue;
									if(visited[nX][nY]) continue;

									visited[nX][nY] = true;
									queue.add(new Node(nX, nY));
								}
							}

						}
					}
				}
				max = Math.max(max, cnt);
			}

			System.out.println("#" + test_case + " "  + max);
		}
	}
}