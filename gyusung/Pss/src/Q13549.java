import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Q13549 {
	public static int MAX_SIZE = 100_000, x, y;
	public static int min = Integer.MAX_VALUE;
	
	public static class Node{
		int loc, dist;
		
		Node(int loc, int dist){
			this.loc = loc;
			this.dist = dist;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		x = sc.nextInt();
		y = sc.nextInt();
	
		boolean[] visited= new boolean[MAX_SIZE + 1];		
		ArrayDeque<Node> queue = new ArrayDeque<>();
		
		queue.add(new Node(x, 0));
		visited[x] = true;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			if(now.loc == y) min = Math.min(min, now.dist);
			
			int next = now.loc * 2;
			if(next < MAX_SIZE+1 && !visited[next]) {
				visited[next] = true;
				queue.add(new Node(next ,now.dist));
			}
			next = now.loc - 1;
			if(next >= 0 && !visited[next]) {
				visited[next] = true;
				queue.add(new Node(now.loc - 1, now.dist + 1));			
			}
			next = now.loc + 1;
			if(next < MAX_SIZE+1 && !visited[next]) {
				visited[next] = true;
				queue.add(new Node(now.loc + 1, now.dist + 1));			
			}
			
		}
		
		System.out.println(min);
	}
}