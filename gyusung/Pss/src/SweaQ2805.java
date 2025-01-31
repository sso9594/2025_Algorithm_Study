import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

class SweaQ2805{
	public static void main(String args[]) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
		int N = sc.nextInt();
			int[][] map = new int[N][N];
			
			String[] strs = new String[N];
			for(int i =0; i<N; i++) {
				strs[i] = sc.next();
			}
			
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = strs[i].charAt(j) - '0';
				}
			}
			
			//System.out.println(Arrays.deepToString(map));
			
			int mid = N/2;
			int ans = 0;
			int s= mid, e = mid;
			
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(j >= s && j <= e) ans += map[i][j];
				}
				
				if(i < mid) {
					s--;
					e++;
				}else {
					s++;
					e--;
				}
			}
			
			System.out.println("#" + test_case + " " + ans);
			
			/////////////////////////////////////////////////////////////////////////////////////////////

		}
	}
}