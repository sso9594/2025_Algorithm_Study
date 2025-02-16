import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class SweaQ1949 {

	public static int N, k, max_dist = 0;
	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {1, -1, 0, 0};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		k = sc.nextInt();
		
		int[][] map = new int[N][N];
		int max = 0;
		int max_cnt = 1;
		
		for(int i =0; i<N;i++) {
			for(int j =0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(max < map[i][j]) {
					max = map[i][j];
					max_cnt = 1;
				}else if(max == map[i][j]) max_cnt++;
			}
		}
	
		int[][] tops = new int[max_cnt][2];
		int cnt = 0;
		for(int i = 0; i<N; i++) {
			for(int j =0; j<N; j++) {
				if(map[i][j] == max) {
					tops[cnt][0] = i;
					tops[cnt][1] = j;
					cnt++;
				}
			}
		}
		
		boolean[][] visited = new boolean[N][N];
		
		for(int i =0; i<max_cnt; i++) {
			//System.out.println(i);
			find(tops[i][0], tops[i][1], bool_deep(visited), int_deep(map), false, 1);
		}
		
		System.out.println(max_dist);
		
	}

	private static void find(int i, int j, boolean[][] v, int[][] map, boolean constructed, int dist) {
		
		if(dist > max_dist) {
			//System.out.println(Arrays.deepToString(map) + " " + i + " " + j );
			max_dist = dist;
		}
		
		v[i][j] = true;
		//System.out.println(Arrays.deepToString(v));
		
		for(int dir = 0; dir<4; dir++) {
		
			int nextX = i + dX[dir];
			int nextY = j + dY[dir];
			
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
			if(v[nextX][nextY]) continue;

			if(map[nextX][nextY] >= map[i][j]) {// 다음 길이 막혀있다면
				// 공사 이미 했음 ==> 못가
				if(constructed) continue;
				//공사 안했고 && 하면 갈 수 있는 길이면 공사ㄱㄱ
				if(!constructed && map[nextX][nextY] - k < map[i][j]) {
					map[nextX][nextY] = map[i][j] - 1;
					find(nextX, nextY,  bool_deep(v), int_deep(map), true, dist + 1);
				}
				continue;
			}
			
			find(nextX, nextY, bool_deep(v), int_deep(map), constructed, dist+1);
		}
		
		
	}
		
	public static boolean[][] bool_deep(boolean[][] v){
		boolean[][] visited = new boolean[N][N];
		for(int i = 0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j] = v[i][j];
			}
		}
		
		return visited;
	}
	
	public static int[][] int_deep(int[][] v){
		int[][] visited = new int[N][N];
		for(int i = 0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j] = v[i][j];
			}
		}
		
		return visited;
	}
	
}
