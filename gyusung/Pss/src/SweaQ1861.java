import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Arrays;

class SweaQ1861
{
    
    	public static class Node{
		int x, y, startX, startY, now;

		public Node(int x, int y, int startX, int startY, int now) {
			super();
			this.x = x;
			this.y = y;
			this.startX = startX;
			this.startY = startY;
			this.now = now;
		}


	}
	
	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {-1, 1, 0, 0};

    
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();
		int[][] map =new int[N][N];
		int[][] max_len = new int[N][N];
		int max = 0;
		
		for(int i =0; i<N;i++) {
			for(int j =0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		ArrayDeque<Node> queue = new ArrayDeque<>();
		//boolean[][] visited = new boolean[N][N];
		
		
		for(int i =0; i<N;i++) {
			for(int j =0; j<N; j++) {
				
				queue.add(new Node(i, j, i, j, 1));
				
				while(!queue.isEmpty()) {
					Node now = queue.poll();
					max_len[now.startX][now.startY] = now.now;
					max = Math.max(max, now.now);
					
					for(int dir = 0; dir<4; dir++) {
						int nX = now.x + dX[dir];
						int nY = now.y + dY[dir];
						
						if(nX < 0|| nX >= N || nY <0 || nY >= N) continue;
						if(map[nX][nY] == map[now.x][now.y] + 1) {
							// 다음이 네 이놈이라면
							queue.add(new Node(nX, nY, now.startX, now.startY, now.now + 1));
						}
						
					}
					
				}
				
			}
		}
		
		int max_loca = Integer.MAX_VALUE;
		for(int i =0; i<N; i++) {
			for(int j =0; j<N; j++) {
				if(max_len[i][j] == max) {
					if(map[i][j] < max_loca) {
						max_loca = map[i][j];
					}
				}
			}
		}
			/////////////////////////////////////////////////////////////////////////////////////////////
System.out.println("#" + test_case + " "  + max_loca + " " + max);
		}
	}
}