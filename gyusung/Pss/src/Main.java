import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main{
	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {1, -1, 0, 0};
	public static int N, M, max;
	public static int[][] dist;
	public static char[][] chs;
	public static HashSet<Integer> set;
	
	
	public static void main(String args[]) throws Exception{
		StringBuilder sb= new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int T;
		T= Integer.parseInt(br.readLine());
		

		
		for(int test_case = 1; test_case <= T; test_case++)
		{

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max = 0;

			String[] strs = new String[N];
			for(int i =0; i<N; i++) {
				strs[i] = br.readLine();
			}

			chs = new char[N][M];
			for(int i =0; i<N; i++) {
				for(int j =0; j<M; j++) {
					chs[i][j] = strs[i].charAt(j);
				}
			}

			dist = new int[N][M];
			set = new HashSet<>();
			set.add(chs[0][0]- 'A');
			dist[0][0] = 1;
			
			dfs(0, 0, 1);
			
			System.out.println("#" + test_case + " " + max);
		}
	}
	
	private static void dfs(int x, int y, int depth) {
		// TODO Auto-generated method stub
		
		max = Math.max(max, depth);
		set.add(chs[x][y] - 'A');
		
		for(int i=0; i<4; i++) {
			int nX = x + dX[i];
			int nY = y + dY[i];
			
			if(nX < 0 || nY < 0 || nX >= N || nY >= M ) continue;
			//if(dist[nX][nY] > depth+1) continue; // 확인필요
			if(set.contains(chs[nX][nY] - 'A')) continue;

			//dist[nX][nY] = depth + 1;
			dfs(nX, nY, depth+1);
			
		}
		set.remove(chs[x][y] - 'A');
	}

}