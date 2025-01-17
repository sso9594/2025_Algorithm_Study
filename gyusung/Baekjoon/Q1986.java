package project_one;
import java.util.Arrays;
import java.util.Scanner;

public class Q1986 {
	public static int[] kX = {-2, -2, -1, -1, 1, 1, 2 ,2};
	public static int[] kY = {1, -1, -2, 2, 2, -2, -1 ,1};
	public static int[] qX = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static int[] qY = {1, 0, -1, 1, -1, 1, 0, -1};
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N][M];
		boolean[][] prohibited = new boolean[N][M];
		
		int q_num = sc.nextInt();
		for(int i =0; i<q_num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			map[x-1][y-1] = 1;
			prohibited[x-1][y-1] = true;
		}
		
		int k_num = sc.nextInt();
		for(int i =0; i<k_num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			map[x-1][y-1] = 2;
			prohibited[x-1][y-1] = true;
		}
		
		int p_num = sc.nextInt();
		for(int i =0; i<p_num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			map[x-1][y-1] = 3;
			prohibited[x-1][y-1] = true;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
			
				if(map[i][j] == 2) {	// 돌다가 k 라면
					
					for(int k=0; k<8; k++) {
						int nkX = i + kX[k];
						int nkY = j + kY[k];
						
						if(nkX < 0 || nkX >=N || nkY < 0 || nkY >= M) continue;
						prohibited[nkX][nkY] = true;
					}
				}else if(map[i][j] == 1) { // 돌다가 Q 라면
					
					for(int q = 0; q<8; q++) {
						
						int len = 1;
						while(true) {
							int nqX = i + (qX[q] * len);	//nqX == 다음 칸
							int nqY = j + (qY[q]* len);	//nqY == 다음 칸
							
							if(nqX < 0 || nqX >=N || nqY < 0 || nqY >= M) break;
							if(map[nqX][nqY] != 0) break;
							
							prohibited[nqX][nqY] = true;
							
							len++;
						}

					}
				}
			}
		}
		
		//System.out.println(Arrays.deepToString(prohibited));
		
		int cnt = 0;
		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				if(!prohibited[i][j]) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
	}
}
