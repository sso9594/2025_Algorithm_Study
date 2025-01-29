import java.util.Arrays;
import java.util.Scanner;

public class Q16943 {

	static int B, len, max = Integer.MIN_VALUE;
	static boolean[] visited;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		String A = sc.next();
		B = sc.nextInt();
		 
		int[] numbers = new int[A.length()];
		for(int i = 0; i<A.length(); i++) {
			numbers[i] = A.charAt(i) - '0';
		}
		
		len = A.length();
		visited = new boolean[len];
		
		recurse(numbers, 0, new int[len]);
		
		if(max == Integer.MIN_VALUE) System.out.println(-1);
		else System.out.println(max);
	}

	private static void recurse(int[] numbers, int idx, int[] sel) {
		// TODO Auto-generated method stub
		
		if(idx >= sel.length) {
			// 비교
			int value = 0;
			for(int i = len-1; i>= 0; i--) {
				value += sel[i] * Math.pow(10, len-i-1);
			}
	//		System.out.print(Arrays.toString(sel) + " ");
	//		System.out.println(value);
			
			if( value > B ) return;
			else {
				max = Math.max(max, value);
		//		System.out.println(max);
			}
			
			return;
		}
		
		for(int i = 0; i<len; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[idx] = numbers[i];
				if(sel[0] != 0) recurse(numbers, idx+1, sel);
				sel[idx] = 0;
				visited[i] = false;
			}
				
		}
		
	}

}
