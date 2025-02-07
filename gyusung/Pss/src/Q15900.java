import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15900 {

	public static boolean[] v;
	public static int sum;
	public static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); 

		list = new ArrayList[N];
		for(int i =0; i<N; i++) {
			list[i] = new ArrayList<>();
		}

		for(int i =0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 

			list[a-1].add(b-1);
			list[b-1].add(a-1);
		}

		
		v = new boolean[N];
		v[0] = true;

		//dfs
		dfs(0, 0);
		//System.out.println(sum);
		
		if(sum % 2 == 0) System.out.println("No");
		else System.out.println("Yes");
	}

	private static void dfs(int now, int depth) {
		// TODO Auto-generated method stub
		
		v[now] = true;
		boolean is_not_leap = false;
		
		for(int i =0; i<list[now].size(); i++) {
			
			int next = list[now].get(i);
			if(v[next]) continue;
			
			dfs(next, depth + 1);
			is_not_leap = true;
		}
		
		if(!is_not_leap) sum += depth;
		
	}
}
