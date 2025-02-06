import java.util.Arrays;
import java.util.Scanner;

public class Q1717 {

	public static int[] par;
	public static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();

		par = new int[N + 1];
		for(int i =0; i <= N; i++) {
			par[i]= i;
		}
		StringBuilder sb = new StringBuilder();
		int quest, a, b, rootA, rootB;
		
		for(int i =0; i<M; i++) {
			quest = sc.nextInt();
			a = sc.nextInt();
			b = sc.nextInt();
			
			rootA = find(a);
			rootB = find(b);
			
			if(quest == 0)par[rootB] = rootA;
			else if(rootA == rootB) sb.append("YES").append('\n');
			else sb.append("NO").append('\n');
		}
		
		System.out.print(sb.toString());
	}
	
	static int find(int now) {
		if(par[now] == now) return now;
		return find(par[now]);
	}
}
