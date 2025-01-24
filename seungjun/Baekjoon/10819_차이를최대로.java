import java.util.Scanner;

public class Main {
	static int N;
	static boolean[] visited;
	static int[] A;
	static int[] res;
	static int answer = Integer.MIN_VALUE;
	
	public static void rec(int x) {
		if (x == N) {
			int temp = 0;
			for (int i=1; i<N; i++) {
				temp += Math.abs(res[i-1]-res[i]);
			}
			answer = Math.max(answer, temp);
			return;
		}
		
		for (int i=0; i<N; i++) {
			if(!visited[i]) {
				res[x] = A[i];
				visited[i] = true;
				rec(x+1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		A = new int[N];
		res = new int[N];
		visited = new boolean[N];
		
		for (int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		rec(0);
		System.out.println(answer);
		
		sc.close();
	}
	
}
