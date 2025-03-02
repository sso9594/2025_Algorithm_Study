import java.util.Arrays;
import java.util.Scanner;

public class Q전기자동차충전소 {

	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {-1, 1, 0, 0};
	public static class Node{
		int x, y;
		
		public Node(int x, int y) {
			this.x =x ;
			this.y =y ;
		}
	}
	public static int once, twice;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		
		tc:for(int test_case = 1; test_case<=T; test_case++) {
			
			int len = 30;
			int[][] map = new int[len][len];
			
			int N = sc.nextInt();
			int[][] houses = new int[N][2];
			int[] houses_dist = new int[N];
			
			for(int i = 0; i<N; i++) {
				houses[i][0] = sc.nextInt() + 15;
				houses[i][1] = sc.nextInt() + 15;
				houses_dist[i] = sc.nextInt();
				
				map[houses[i][0]][houses[i][1]] = i+1;
			}
			
			// 1개로 가능한 경우
			once = Integer.MAX_VALUE;
			for(int i =0; i<len; i++) {
				A: for(int j = 0; j < len; j++) {
					
					if(map[i][j] > 0) continue;
					
					// 불가한 집 있으면 out
					for(int n = 0; n<N; n++) {
						if(!is_possible(i, j, houses[n][0], houses[n][1], houses_dist[n])) continue A;
					}
					
					//갱신
					int sum = 0;
					for(int n = 0; n<N; n++) {
						sum += get_dist(i, j, houses[n][0], houses[n][1]);
					}
					
					once = Math.min(sum, once);
				}
			}
			if(once < Integer.MAX_VALUE) {
				sb.append("#"+ test_case + " " + once).append('\n');
				continue tc;
			}
			
			//2개 선택
			twice = Integer.MAX_VALUE;
			int[] dists;
			
			for(int i =0; i<len*len; i++) {
				int rechargeX1 = i/len;
				int rechargeY1= i%len;
				
				if(map[rechargeX1][rechargeY1] > 0) continue;
				A:for (int j = i+1; j <len*len; j++) {
					int rechargeX2 = j/len;
					int rechargeY2 = j%len;
					
					if(map[rechargeX2][rechargeY2] > 0) continue A;
					//if(rechargeX1 == rechargeX2 && rechargeY1 == rechargeY2) continue A;
					//System.out.println(rechargeX1 + " " + rechargeY1 +", " +rechargeX2+ " " + rechargeY2);
					
					//집이 아닌 위치 2곳 선정 완료
					dists = new int[N];
					for(int n = 0; n<N; n++) {		// recharge1 부터 확인
						if(is_possible(rechargeX1, rechargeY1, houses[n][0], houses[n][1], houses_dist[n])) {
							dists[n] = get_dist(rechargeX1, rechargeY1, houses[n][0], houses[n][1]);
						}
					}
					for(int n =0; n<N; n++) {		// recharge2 확인 (둘 중 가까운 곳으로 거리 선정)
						if(is_possible(rechargeX2, rechargeY2, houses[n][0], houses[n][1], houses_dist[n])) {
							if(dists[n] > 0) {							
								dists[n] = Math.min(dists[n], get_dist(rechargeX2, rechargeY2, houses[n][0], houses[n][1]));
							}else {
								dists[n] = get_dist(rechargeX2, rechargeY2, houses[n][0], houses[n][1]);
							}
						}
					}
					
					//System.out.println(Arrays.toString(dists));
					int sum = 0;
					for(int n=0; n<N; n++) {
						if(dists[n] == 0) continue A;
						else sum += dists[n];
					}
					twice = Math.min(twice, sum);
				} 
			}
			
			if(twice == Integer.MAX_VALUE) {
				sb.append("#"+test_case + " -1").append('\n');
			}else {
				sb.append("#"+test_case + " " + twice).append('\n');		
			}
		}
		System.out.print(sb.toString());
	}

	private static int get_dist(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	private static boolean is_possible(int x1, int y1, int x2, int y2, int d) {
		// TODO Auto-generated method stub
		if(Math.abs(x1 - x2) + Math.abs(y1 - y2) > d) return false;
		else return true;
	}

}
