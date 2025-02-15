import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Q1068 {
	
	public static ArrayList<Integer>[] list;
	public static int pre_leaf, aft_leaf;
	public static boolean[] no_leaf;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i =0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int M = sc.nextInt();
		no_leaf = new boolean[N];
		
		int start = 0;
		for(int i = 0; i<N; i++) {
			if(arr[i] == -1) start = i;
		}
		
		list = new ArrayList[N];
		for(int i=0; i<N; i++) list[i] = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			int child = i;
			int	parent = arr[i];
			
			if(parent == -1) continue;
			list[parent].add(child);
		}
	
		find_leaf(start, 0);
		no_leaf[M] = true;
		//System.out.println(Arrays.toString(no_leaf));
		if(!no_leaf[start]) {
			//System.out.println("어쩌구");
			find_leaf(start, 1);
		}
	
		//System.out.println(pre_leaf);
		System.out.println(aft_leaf);
	}

	private static void find_leaf(int m, int to) {
		// TODO Auto-generated method stub

		int now_child = 0;
		for(int i =0; i<list[m].size(); i++) {
			int next = list[m].get(i);
			if(no_leaf[next] == false) now_child++;
		}
		
		if(now_child == 0 && to == 0) {
			//System.out.println("pre : " + m);
			pre_leaf++;
		}else if(now_child == 0 && to == 1) {
			//System.out.println("aft :" + m);
			aft_leaf++;
		}else {
			for(int i = 0; i<list[m].size(); i++) {
				int next = list[m].get(i);
				if(!no_leaf[next]) {
					//System.out.println("find: " + m);
					find_leaf(next, to);
				}
			}
		}
	}
}
