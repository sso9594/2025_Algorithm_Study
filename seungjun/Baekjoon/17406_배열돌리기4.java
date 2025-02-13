/* 배열 돌리기 4 */
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    static int N, M, K, ans;
    static int[][] arr, tmp;
    
    // 해당 사이클 1회전 후 tmp 배열에 할당
    static void rotate_arr(Deque<Integer> deque, int sr1, int sc1, int sr2, int sc2, int size, int n) {

    	// 1회전
    	deque.addFirst(deque.pollLast());

        // →
        for (int c1 = sc1 + n; c1 < sc2 - n + 1; c1++) {
        	tmp[n + sr1][c1] = deque.pollFirst();
        }
        // ↓
        for (int r1 = n + sr1 + 1; r1 < sr2 - n + 1; r1++) {
        	tmp[r1][sc2 - n] = deque.pollFirst();
        }
        // ←
        for (int c2 = sc2 - n - 1; c2 >= n + sc1; c2--) {
        	tmp[sr2 - n][c2] = deque.pollFirst();
        }
        // ↑
        for (int r2 = sr2 - n - 1; r2 > n + sr1; r2--) {
        	tmp[r2][n + sc1] = deque.pollFirst();
        }
    }
    
    // 각 사이클 값을 큐에 1차원으로 저장
    static void cycle_value(int r, int c, int s) {
        Deque<Integer> deque = new ArrayDeque<>();
    	int size = 2*s+1;

        int sr1 = r-s-1, sc1 = c-s-1;
        int sr2 = r+s-1, sc2 = c+s-1;
        
        for (int n=0; n<size/2; n++) {
            deque = new ArrayDeque<>();
            
            // →
            for (int c1=sc1+n; c1<sc2-n+1; c1++) {
                deque.add(tmp[n+sr1][c1]);
            }
            // ↓
            for (int r1=n+sr1+1; r1<sr2-n+1; r1++) {
                deque.add(tmp[r1][sc2-n]);
            }
            // ←
            for (int c2=sc2-n-1; c2>=n+sc1; c2--) {
                deque.add(tmp[sr2-n][c2]);
            }
            // ↑
            for (int r2=sr2-n-1; r2>n+sr1; r2--) {
                deque.add(tmp[r2][n+sc1]);
            }

            // 각 사이클 회전
            rotate_arr(deque, sr1, sc1, sr2, sc2, size, n);
        }
    }
    
    // 0~k의 순열 -> 최소값 찾기 
    static void rec(int[][] row, int[] sel, int n, boolean[] v) {
    	if (n == K) {
    		for (int i: sel) {
    			int r = row[i][0];
    			int c = row[i][1];
    			int s = row[i][2];
    			cycle_value(r, c, s);
    		}
    		
    		for (int i=0; i<N; i++) {
            	int row_value = 0;
                for (int j=0; j<M; j++) {
                	row_value += tmp[i][j];
                }
                ans = Math.min(ans, row_value);
            }
    		tmp_reset();
    		return;
    	}
    	
    	for (int i=0; i<K; i++) {
    		if (!v[i]) {
    			sel[n] = i;
    			v[i] = true;
    			rec(row, sel, n+1, v);
    			v[i] = false;
    		}
    	}
    }
    
    // tmp배열에 원본배열 복사(초기화)
    static void tmp_reset() {
		for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
            	tmp[i][j] = arr[i][j];
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        K = input.nextInt();
        arr = new int[N][M];
        tmp = new int[N][M];
        int[][] rot = new int[K][3]; // 회전 연산 저장 배열

        ans = Integer.MAX_VALUE;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                arr[i][j] = input.nextInt();
            }
        }
        tmp_reset();
        
        // 회전 연산 저장
        for (int k=0; k<K; k++) {
            int r = input.nextInt();
            int c = input.nextInt();
            int s = input.nextInt();
            
            rot[k][0] = r;
            rot[k][1] = c;
            rot[k][2] = s;
        }
        
        // 회전 연산 모든 순서 구하기
        rec(rot, new int[K], 0, new boolean[K]);

        System.out.println(ans);
        input.close();
    }
}