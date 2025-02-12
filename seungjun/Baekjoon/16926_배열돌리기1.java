import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
	static int N, M, R;
	static String[][] arr, res;
	
	static void rotate_arr(Deque<String> temp, int n) {
		
		// R번 회전
		for (int r=0; r<R; r++) {
			temp.addFirst(temp.pollLast());
		}
		
		/* 회전 후 값 할당 */
		// ↓
		for (int r1=n-1; r1<=N-n; r1++) {
			res[r1][n-1] = temp.poll();
		}
		
		// →
		for (int c1=n; c1<=M-n; c1++) {
			res[N-n][c1] = temp.poll();
		}
		
		// ↑
		for (int r2=N-n-1; r2>n-1; r2--) {
			res[r2][M-n] = temp.poll();
		}
		
		// ←
		for (int c2=M-n; c2>n-1; c2--) {
			res[n-1][c2] = temp.poll();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		arr = new String[N][M];
		res = new String[N][M];
		Deque<String> deque = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				arr[i][j] = sc.next();
			}
		}
		
		// 각 사이클 추출
		for (int n=1; n<=Math.min(N, M)/2; n++) {
			deque = new ArrayDeque<>();
			// ↓
			for (int r1=n-1; r1<=N-n; r1++) {
				deque.add(arr[r1][n-1]);
			}
			
			// →
			for (int c1=n; c1<=M-n; c1++) {
				deque.add(arr[N-n][c1]);
			}
			
			// ↑
			for (int r2=N-n-1; r2>n-1; r2--) {
				deque.add(arr[r2][M-n]);
			}
			
			// ←
			for (int c2=M-n; c2>n-1; c2--) {
				deque.add(arr[n-1][c2]);
			}
			
			// 각 사이클 회전
			rotate_arr(deque, n);
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
        sc.close();
	}
}