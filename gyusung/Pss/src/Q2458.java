import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Q2458 {

	public static ArrayList<Integer>[] list;
	public static ArrayList<Integer>[] reverse;
	public static ArrayDeque<Integer> queue;
	public static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int cnt = 0;
		
		queue = new ArrayDeque<>();
		
		list = new ArrayList[N];
		reverse= new ArrayList[N];
		for(int i =0; i<N; i++) {
			list[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for(int i =0; i<M; i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
		
			list[a].add(b);
			reverse[b].add(a);
		}
		
		v = new boolean[N];
		
		A:for(int k = 0; k<N; k++) {
			Arrays.fill(v, false);
			
            // k에서 출발해서 다른 학생들 탐색 (자신보다 작은 학생)
            bfs(k, v, list);
            // k에서 출발해서 다른 학생들 탐색 (자신보다 큰 학생)
            bfs(k, v, reverse);
            
            for (int i = 0; i < N; i++) {
                if(!v[i]) continue A;
            }
            
            cnt++;
		}
		
		System.out.println(cnt);
	}

	private static void bfs(int k, boolean[] v, ArrayList<Integer>[] graph) {
		// TODO Auto-generated method stub
		
		v[k] = true;
 		queue.add(k);

		while(!queue.isEmpty()) {
			int now = queue.poll();
			//v[now] = true;
			
			for(int i =0; i<graph[now].size(); i++) {
				
				int next = graph[now].get(i);
				if(v[next]) continue;
				
				queue.add(next);
				v[next] = true;
			}
			
		}
	}
}
