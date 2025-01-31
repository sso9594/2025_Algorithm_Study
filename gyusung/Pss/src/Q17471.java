import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Q17471 {
	
	public static boolean[] visited;
	public static boolean touched;
	public static int N, min = Integer.MAX_VALUE;
	public static int[] popul;
	public static ArrayList<Integer>[] list;
	
	public static void main(String[] args) {
			
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		popul = new int[N];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			popul[i] = sc.nextInt();
		}
		
		list = new ArrayList[N];
		for(int i =0; i<N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i =0; i<N; i++) {
			int adjAreaNum = sc.nextInt();
			
			for(int j =0; j<adjAreaNum; j++) {
				int area = sc.nextInt();
				list[i].add(area -1 );
			}
		}
		
		// list에 들어갔는지
//		for(int i =0; i<N; i++) {
//			int len = list[i].size();
//			for(int j=0; j<len; j++) {
//				System.out.print(list[i].get(j).next);
//			}
//			System.out.println();
//		}
		
		// 부분집합
		recurse(new int[N], 0);
		if(touched) System.out.println(min);
		else System.out.println("-1");
	}

	private static void recurse(int[] sel, int idx) {
		// TODO Auto-generated method stub
		for(int i = idx; i<sel.length; i++) {
			sel[i] = 1;
			recurse(sel, i+1);
			sel[i] = 0;
		}

		ArrayList<Integer> list_true = new ArrayList<>();
		ArrayList<Integer> list_false = new ArrayList<>();
		
		for(int i =0; i<sel.length;i++) {
			if(sel[i] == 1) {
				list_true.add(i);
			}else {
				list_false.add(i);
			}
		}
		isValid(list_true.stream().mapToInt(Integer::intValue).toArray(), list_false.stream().mapToInt(Integer::intValue).toArray());
	}

	private static void isValid(int[] trues, int[] falses) {
		// TODO Auto-generated method stub
	
		if(trues.length == 0) return;
		if(falses.length == 0) return;
		
		boolean[] visited = new boolean[N];
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		queue.add(trues[0]);
		visited[trues[0]] = true;
	
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visited[now] = true;
			
			for(int i =0; i<list[now].size(); i++) {
				for(int j =0; j<trues.length; j++) {
					
					if(list[now].get(i) == trues[j]) {
						if(!visited[list[now].get(i)])
							queue.add(list[now].get(i));		
					}
					
				}
			}
		}
		
		// true 배열의 모든 곳에 접근이 가능한지 확인
		 for(int i=0; i<trues.length; i++) {
			 if(!visited[trues[i]]) {
				 return;
			 }
		 }
//		 System.out.println(Arrays.toString(trues));
//		 System.out.println(Arrays.toString(visited));		 
		 
		 Arrays.fill(visited, false);
		
		queue.add(falses[0]);
		visited[falses[0]] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visited[now] = true;
			
			for(int i =0; i<list[now].size(); i++) {
				for(int j =0; j<falses.length; j++) {
					if(list[now].get(i) == falses[j]) {
						if(!visited[list[now].get(i)]) queue.add(list[now].get(i));		
					}
				}
			}
		}
		
		// falses 배열의 모든 곳에 접근이 가능한지 확인
		 for(int i=0; i<falses.length; i++) {
			 if(!visited[falses[i]]) {
				 //min = -1;
				 return;
			 }
		 }
		 
//		 System.out.println(Arrays.toString(falses));
//		 System.out.println(Arrays.toString(visited));
		 
		 touched = true;
		 // 최솟값 비교
		 int true_total = 0;
		 int false_total = 0;
		 for(int i=0; i<trues.length; i++) {
			 true_total += popul[trues[i]];
		 }
		 
		 for(int i=0; i<falses.length; i++) {
			 false_total += popul[falses[i]];
		 }
		 
		 int now_min = Math.abs(true_total - false_total);
		 min = Math.min(min, now_min);
		 
	}
}
