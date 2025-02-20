import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
    
    public static int[][] cores, ans_map;
	public static int N, to_connect;
	public static int max_core = 0, min_len = Integer.MAX_VALUE;
	public static int[] dX = {0, 1, 0, -1};
	public static int[] dY = {1, 0, -1, 0};
    
	public static void main(String args[]) throws Exception
	{
	
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
		N = sc.nextInt();
		int[][] map = new int[N][N];
		
		to_connect = 0;
		for(int i =0; i<N; i++) {
			for(int j =0; j<N; j++) {
				map[i][j] = sc.nextInt();
				
				if(map[i][j] == 1) {
					if(i <= 0 || i >=N-1 || j <=0 || j>=N-1) continue;
					to_connect++;
				}
			}
		}
		
		cores = new int[to_connect][2];
		
		int k = 0;
		for(int i =0; i<N; i++) {
			for(int j =0; j<N; j++) {
				if(map[i][j] == 1) {
					if(i <= 0 || i >= N-1 || j <=0 || j>=N-1) continue;
					cores[k][0] = i;
					cores[k][1] = j;
					k++;
				}
			}
		}
		
		dfs(deepCopy(map), 0, 0, 0);
		
		System.out.println("#"+ test_case + " " + min_len);		
            
            max_core = 0;
		min_len = Integer.MAX_VALUE;

		}
	}
    
    public static void dfs(int[][] map, int idx, int connected, int len) {
		if((to_connect - idx) + connected < max_core) return;
		if(idx >= to_connect) {
			
			if(connected >= max_core) {
				if(connected > max_core) {
					max_core = connected;
					min_len = len;
					ans_map = deepCopy(map);
				}else if(connected == max_core) {
					if(min_len > len) {
						min_len = len;
						ans_map = deepCopy(map);
					}	
				}
			}
			return;
		}
		
		// 사방에 대한 팔 뻗기
		A : for(int dir = 0; dir < 4; dir++) {
			
			int[][] new_map = deepCopy(map);
			
			int nowX = cores[idx][0];
			int nowY = cores[idx][1];
			
			while(nowX > 0 && nowX < N-1 && nowY > 0 && nowY < N-1) {
				nowX += dX[dir];
				nowY += dY[dir];
				
				if(map[nowX][nowY] == 1) { // 다른 프로세서 만나는 경우
					dfs(deepCopy(map), idx+1, connected, len);
					continue A;
				}
			}
			
			nowX = cores[idx][0];
			nowY = cores[idx][1];
			
			//System.out.println(nowX + " " + nowY);
			
			int now_len = 0;
			while(nowX > 0 && nowX < N-1 && nowY > 0 && nowY < N-1) {
				nowX += dX[dir];
				nowY += dY[dir];
			
				new_map[nowX][nowY] = 1;
				now_len ++;
			}
			
			
			dfs(deepCopy(new_map), idx+1, connected+1, len + now_len);
		}
	}
	
	public static int[][] deepCopy(int[][] map){
		int[][] new_map = new int[N][N];
		
		for(int i =0; i<N; i++) {
			for(int j=0; j<N; j++) {
				new_map[i][j] = map[i][j];
			}
		}
		
		return new_map;
	}
}