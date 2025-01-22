import java.util.Arrays;
import java.util.Scanner;

public class Q11404 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] buses = new int[M][3];
		int[][] dp = new int[N][N];
		for(int i =0; i<N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][i] = 0;
		}
		
		for(int i =0; i<M; i++) {
			buses[i][0] = sc.nextInt();
			buses[i][1] = sc.nextInt();
			buses[i][2] = sc.nextInt();
			
			if(dp[buses[i][0]-1][buses[i][1]-1] > buses[i][2]) dp[buses[i][0]-1][buses[i][1]-1] = buses[i][2];
		}
		
		for(int k=0; k<N; k++) {
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(dp[i][k] > Integer.MAX_VALUE -1) continue;
					if(dp[k][j] > Integer.MAX_VALUE -1) continue;
					
					if(dp[i][j] > dp[i][k] + dp[k][j]) dp[i][j] = dp[i][k] + dp[k][j];
				}
			}
		}
		
		for(int i =0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dp[i][j] > Integer.MAX_VALUE- 1) dp[i][j] = 0; 
				
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
