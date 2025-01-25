import java.util.Arrays;
import java.util.Scanner;

public class Q2580 {

	static int zeros;
	static int[][] zeroList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int[][] map = new int[9][9];
		
		for(int i =0 ;i<9; i++) {
			for(int j= 0; j<9;j++) {
				map[i][j] = sc.nextInt();
				
				if(map[i][j] == 0) zeros++; 
			}
		}
		
		zeroList = new int[zeros][2];
		int k = 0; 
		
		for(int i =0 ;i<9; i++) {
			for(int j= 0; j<9;j++) {				
				if(map[i][j] == 0) {
					zeroList[k][0] = i; 
					zeroList[k][1] = j;
					k++;
				}
			}
		}
		
		//System.out.println(Arrays.deepToString(zeroList));
		dfs(map, 0);
		
		
	}

	private static void dfs(int[][] map, int idx) {
		// TODO Auto-generated method stub
		System.out.println(idx);
		System.out.println(Arrays.deepToString(map));     
		if(idx == zeros) { // 종료 조건
			for(int i=0; i<9; i++) {
				for(int j =0; j<9; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		
			System.exit(0);
		}
		
		for(int i =1; i<10; i++) {

			map[zeroList[idx][0]][zeroList[idx][1]] = i;
			
			if(is_ok_horiz(map, zeroList[idx][0], zeroList[idx][1])) {
				if(is_ok_verti(map, zeroList[idx][0], zeroList[idx][1])) {
					if(is_ok_square(map, zeroList[idx][0], zeroList[idx][1])){
						
						
						dfs(map, idx+1);
					
					
					}else continue;
				}else continue;
			}else continue;
			
			
		}
		map[zeroList[idx][0]][zeroList[idx][1]] = 0;
	}

	private static boolean is_ok_square(int[][] map, int i, int j) {
		// TODO Auto-generated method stub
		int now = map[i][j];
		
		int startX = (i/3)*3;
		int startY = (j/3)*3;
		
		for(int x = startX; x<startX+3; x++) {
			for(int y=startY; y<startY+3; y++) {
				if(x == i && y ==j) continue;
				
				if(map[x][y] == now) return false;
			}
		}
		
		return true;
	}

	private static boolean is_ok_verti(int[][] map, int i, int j) {
		// TODO Auto-generated method stub
		int now = map[i][j];

		for(int x = 0; x < 9; x++) {
			if(x == i) continue;
			if(now == map[x][j]) return false;
		}
		
		return true;
		}

	private static boolean is_ok_horiz(int[][] map, int i, int j) {
		// TODO Auto-generated method stub
		int now = map[i][j];

		for(int x = 0; x < 9; x++) {
			if(x == j) continue;
			if(now == map[i][x]) return false;
		}
		
		return true;
	}

}
