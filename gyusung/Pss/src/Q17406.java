import java.util.Arrays;
import java.util.Scanner;

public class Q17406 {

	public static int N, M, k,min = Integer.MAX_VALUE;
	public static int[][] init_map, map, requests;
	public static boolean[] visited;
 	public static int[] arrr;
	
 	public static void init() {
 		for(int i=0; i<N; i++) {
 			for(int j =0; j<M;j++) {
 				map[i][j] = init_map[i][j];
 			}
 		}
 	}
 	
 	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc= new Scanner (System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		k = sc.nextInt();
		
		init_map = new int[N][M];
		map = new int[N][M];
		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				init_map[i][j] = map[i][j] = sc.nextInt();
			}
		}
		
		requests = new int[k][3];
		visited = new boolean[k];
		arrr = new int[k];
		
		for(int i =0; i<k; i++) {
			requests[i][0] = sc.nextInt()-1;
			requests[i][1] = sc.nextInt()-1;
			requests[i][2] = sc.nextInt();
		}
	
		dfs(0, 0);
		
		System.out.println(min);
	}

	private static void dfs(int now, int sum) {
		// TODO Auto-generated method stub
		
		if(sum >= k) {
			init();
			//System.out.println(Arrays.toString(arrr));
			for(int i = 0; i<k; i++) {	// 모든 연산 사용
				int r = requests[arrr[i]][0];
				int c = requests[arrr[i]][1];
				int s = requests[arrr[i]][2];
				
				for(int j = 1; j<=s; j++) { // 모든 바퀴 돌기
					spin_arr(r, c, j, s);
				}
			}	
			//System.out.println(Arrays.deepToString(map));
			for(int i=0; i<N; i++) {
				int hangSum = Arrays.stream(map[i]).sum();
				if(hangSum < min) min = hangSum;
			}
			
		}
		
		for(int i = 0; i < k; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arrr[sum] = i;
				
				dfs(i, sum+1);
				
				visited[i] = false;
			}
		}
	}

	private static void spin_arr(int r, int c, int size, int limit) {
		// TODO Auto-generated method stub
		int tmp = map[r-size][c-size];
		
		for(int i = r-size; i<r+size; i++) {
			map[i][c-size] = map[i+1][c-size];
		}
		for(int i = c-size; i<c+size; i++) {
			map[r+size][i] = map[r+size][i+1];
		}
		for(int i = r+size; i> r-size; i--) {
			map[i][c+size] = map[i-1][c+size];
		}
		for(int i = c+size; i> c-size; i--) {
			map[r-size][i] = map[r-size][i-1];
		}
		
		map[r-size][c-size + 1] = tmp;
		//System.out.println(Arrays.deepToString(map));
	
	}
}
