import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q1941 {

	public static char[][] map;
	public static int ans;
	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {1, -1, 0, 0};
	public static Set<String> set;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc= new Scanner(System.in);
		
		String[] strs = new String[5];
		for(int i =0; i<5; i++) {
			strs[i] = sc.next();
		}

		map = new char[5][5];
		for(int i =0; i<5; i++) {
			for(int j=0; j<5; j++) {
				map[i][j] = strs[i].charAt(j);
			}
		}

		set = new HashSet<String>();

		for(int i =0; i<5; i++) {
			for(int j=0; j<5; j++) {

				if(map[i][j] == 'S') dfs(new int[7][2], i, j, 0, 1, 0);
				else dfs(new int[7][2], i, j, 0, 0, 1);
			}
		}

		System.out.print(set.size());
	}

	private static void dfs(int[][] selected, int x, int y, int depth, int s_cnt, int y_cnt) {
		// TODO Auto-generated method stub

		//종료조건
		if(y_cnt >= 4) return;
		if(depth >= 6 ) {
			if(y_cnt < 3 || map[x][y] == 'S') {
				selected[depth][0] = x;
				selected[depth][1] = y;

				Arrays.sort(selected, (o1, o2) -> o1[0]==o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0],o2[0]));
				//System.out.println(Arrays.deepToString(selected) + s_cnt+ " " + y_cnt);

				String str = make_str(selected);

				if(!set.contains(str)) {
					//System.out.println(Arrays.deepToString(selected) + s_cnt+ " " + y_cnt);
					set.add(str);
				}
			}
			return;
		}

		// 지금 들어온 칸 추가
		selected[depth][0] = x;
		selected[depth][1] = y;
		
		//인접하는 7칸 만들기
		for(int i =0; i<=depth; i++) {

			int nowX = selected[i][0];
			int nowY = selected[i][1];

			A:for(int dir = 0; dir<4; dir++) {
				int nextX = nowX + dX[dir];
				int nextY = nowY + dY[dir];

				if(nextX < 0 || nextY < 0 || nextX >=5 || nextY >= 5) continue;
				for(int a = 0; a<=depth; a++) {
					if(selected[a][0] == nextX && selected[a][1] == nextY) continue A;
				}

				if(map[nextX][nextY] == 'S') {
					dfs(selected.clone(), nextX, nextY, depth + 1, s_cnt + 1, y_cnt);
				}else if(map[nextX][nextY] == 'Y'){
					dfs(selected.clone(), nextX, nextY, depth + 1, s_cnt, y_cnt + 1);
				}
			}
		}
	}

	private static String make_str(int[][] selected) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<7; i++) {
			for(int j=0; j<2; j++) {
				sb.append(selected[i][j]);
			}
		}
		return sb.toString();
	}
}