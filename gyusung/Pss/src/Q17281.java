import java.util.Arrays;
import java.util.Scanner;

public class Q17281 {
	
	public static boolean[] visited;
	public static int[] toMake, hitter;
	public static int[][] map;
	public static int N, next_hitter, game_score, ans;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][9];
		
		for(int i =0; i<N; i++) {
			for(int j =0; j<9; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		visited = new boolean[8];
		toMake = new int[8];
		hitter = new int[9];
		next_hitter = 0;
		game_score = 0;
		ans = 0;
		
		//순서 결정
		dfs(0, 0);
		
		System.out.println(ans);
		
	}

	private static void dfs(int now, int sum) {
		// TODO Auto-generated method stub
		
		if(sum >= 8) {
			//System.out.println(Arrays.toString(toMake));
			next_hitter = 0;
			for(int inning = 0; inning<N; inning++){
				hitter[3] = map[inning][0];
				for(int i=0; i<9; i++){
					if(i <= 2) hitter[i] = map[inning][toMake[i]+1];
					else if(i == 3) continue;
					else hitter[i] = map[inning][toMake[i-1]+1];
				}
				//System.out.println(Arrays.toString(hitter));	
				start_inning(inning);
			}
			
			//System.out.println("총 점수 : " + game_score);
			ans = Math.max(ans, game_score);
			game_score = 0;
			
			return;
		}
		
		for(int i = 0; i<8; i++) {
			if(!visited[i]) {
				visited[i] = true;
				
				toMake[sum] = i;
				dfs(i, sum+1);
				
				visited[i] = false;
			}
		}
		
	}

	private static void start_inning(int inning) {
		// TODO Auto-generated method stub
		
		boolean[] bases = new boolean[3];
		int out_cnt = 0;
		int i = next_hitter;
		int whos_hitter = 0;
		int inning_score = 0;

		while(out_cnt < 3) {
			whos_hitter = i % 9;
			// 미리 다음타자 선정
			i++;
			next_hitter = i % 9;
			
			if(hitter[whos_hitter] == 0) {
				out_cnt++;
			//	System.out.println(whos_hitter + "번 타자에서 out");
				if(out_cnt == 3) {
				//	System.out.println(inning_score + " 점에서 이닝종료");
					break;
				}
			}else if(hitter[whos_hitter] == 1){
				if(bases[2]) inning_score++;
				bases[2] = bases[1];
				bases[1] = bases[0];
				bases[0] = true;
			}else if(hitter[whos_hitter] == 2) {
				if(bases[2]) inning_score++;
				if(bases[1]) inning_score++;
				bases[2] = bases[0];
				bases[1] = true;
				bases[0] = false;
			}else if(hitter[whos_hitter] == 3) {
				if(bases[2]) inning_score++;
				if(bases[1]) inning_score++;
				if(bases[0]) inning_score++;
				bases[2] = true;
				bases[1] = false;
				bases[0] = false;
			}else if(hitter[whos_hitter] == 4) {
				if(bases[2]) inning_score++;
				if(bases[1]) inning_score++;
				if(bases[0]) inning_score++;
				inning_score++;
				bases[2] = false;
				bases[1] = false;
				bases[0] = false;
			}
		}

		//System.out.println(inning + "회에 " + inning_score + "점");
		game_score += inning_score;
	}
}
