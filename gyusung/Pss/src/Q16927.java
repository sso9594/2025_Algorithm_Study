import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q16927 {

	public static int N, M, R, min;
	public static int[][] arr;
	public static int[] dX = { 1, 0, -1, 0 };
	public static int[] dY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		min = Math.min(N, M);

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < Math.min(N, M) / 2; i++) {
			int len = (N - (i * 2)) * 2 + (M - (i * 2)) * 2 - 4;

			solve(i, len);
		}

		for(int i =0; i<N; i++) {
			for(int j =0; j<M; j++) {
				System.out.print(arr[i][j] +" ");
			}
			System.out.println();
		}

	}

	private static void solve(int idx, int len) {
		// TODO Auto-generated method stub
		for(int i=0; i< R % len; i++) {
			int x1 = idx;
			int y1 = idx;
			
			int k = 0;
			int tmp = arr[idx][idx];
			
			while( k < 4 ) {
				int nX = x1 + dX[k];
				int nY = y1 + dY[k];

				if(nX < idx || nX >= M - idx || nY < idx || nY >= N - idx) { 
					k++;
					continue; 
				}

				//arr[x1][y1] = arr[nX][nY];
				//다음 이동 칸의 값을 현재 칸으로
				arr[y1][x1] = arr[nY][nX];
				
				//x1, y1 값을 다음 값으로 변동
				x1 = nX;
				y1 = nY;
				
			}
			//복제되어 있는 값(idx+1, idx)을 정상화
			arr[idx+1][idx] = tmp;
		}
		//System.out.println(Arrays.deepToString(arr));
	}
}
