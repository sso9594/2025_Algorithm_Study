import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Sol2468 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] map = new int[n][n];
		
		int max = 0;
        int min = Integer.MAX_VALUE;
		for(int i = 0; i< n;i++) {
			for(int j = 0; j< n; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] > max) {
					max = map[i][j];
				}
                if(map[i][j] < min){
                    min = map[i][j];
                }
			}
		}
		
        if(min == max){
            System.out.println(min);
        }else{
            int maxResult = 0;
            for(int nongdo = min; nongdo <= max; nongdo++) {
                int result = bfs(map, n, nongdo);
                if(maxResult < result) maxResult = result;
            }
            
            System.out.println(maxResult);
        }

		}
	
	static int bfs(int[][] map, int n, int nongdo) {
		//setting
		boolean[][] v = new boolean[n][n];
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		
		int result = 0;
		
		//시작 포인트 설정
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int x = i;
				int y = j;
				
				if(v[x][y] || map[x][y] <= nongdo) continue; // 방문하거나 농도보다 낮으면 안감
				
				 Queue<int[]> queue = new ArrayDeque();
				 queue.offer(new int[] {x,y});
				 v[i][j] = true;
				 
				 while(!queue.isEmpty()) {
					 int[] pos = queue.poll();
					 int xdx;
					 int ydy;
					 for(int k = 0; k < 4; k++) {
						 xdx = pos[0] + dx[k];
						 ydy = pos[1] + dy[k];
						 
						 if(xdx >= 0 && xdx < n && ydy >= 0 && ydy < n && v[xdx][ydy] == false && map[xdx][ydy] > nongdo) {
							 queue.offer(new int[] {xdx,ydy});
							 v[xdx][ydy] = true;
						 }
					 }
				 }
				 result++;
				 }
			}
		return result;
	}
		
		
	}


