import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N, L, R, day;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 나라 간에 L이상 R사이의 인구 차이가 존재하면 
		while(check_diff()) {
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) {
						// 각 나라마다 인구차이 계산 후 이동 시작
						BFS(i, j);
					}
				}
			}
			// 인구 이동 후 다음날에 바뀐 나라들의 인구수와 인접한 나라들의 인구수를 다시 계산하기 위함
			visited = new boolean[N][N];
			day++; // +1일
		}
		System.out.println(day);
		sc.close();
	}
	
	static void BFS(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<>();
		ArrayList<int[]> curCuntry = new ArrayList<>(); // 현재 나라로부터 인접한 연합국들 저장
		
		visited[i][j] = true;
		queue.add(new int[] {i, j});
		curCuntry.add(new int[] {i, j});
		int sum = map[i][j];
		
		// 인접한 나라와 인구 차이 계산 후 연합국 형성
		while (!queue.isEmpty()) {
			int[] p = queue.poll();
			int r = p[0], c = p[1];
			
			for (int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr>=0 && nr<N && nc>=0 && nc<N) {
					// 인구차이 계산
					int pop_diff = Math.abs(map[r][c] - map[nr][nc]);
					if (pop_diff>=L && pop_diff<=R && !visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new int[] {nr, nc});
						curCuntry.add(new int[] {nr, nc}); // 연합국 리스트에 추가
						sum += map[nr][nc]; // 인구 수 합산
					}
				}
			}
		}
		
		// 연합국들의 평균 인구수 계산 후 각 나라에 할당
		for (int[] p : curCuntry) {
			map[p[0]][p[1]] = sum / curCuntry.size();
		}
	}
	
	// 인접한 나라간의 인구수 차이인 L이상 R이하 존재여부 확인
	static boolean check_diff() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				for (int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (nr>=0 && nr<N && nc>=0 && nc<N) {
						int pop_diff = Math.abs(map[i][j] - map[nr][nc]);
						if (pop_diff>=L && pop_diff<=R) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}