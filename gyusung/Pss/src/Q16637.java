import java.util.Arrays;
import java.util.Scanner;

public class Q16637 {

	public static int num_len;
	public static int ans = Integer.MIN_VALUE;
	public static char[] opers;
	public static int[] nums;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String str = sc.next();
		
		nums = new int[N/2 + 1];
		opers = new char[N/2];
		
		num_len = nums.length;
		
		int odd = 0, even = 0;
		for(int i =0; i<N; i++) {
			if(i % 2 == 0) {
				nums[even] = str.charAt(i) - '0';
				even++;
			}else {
				opers[odd] = str.charAt(i);
				odd++;
			}
		}

//		System.out.println(Arrays.toString(nums));
//		System.out.println(Arrays.toString(opers));

		dfs(nums[0], 0);
		
		System.out.println(ans);
	}

	private static void dfs(int sum, int index) {
		// TODO Auto-generated method stub
		if(index >= num_len-1) {
			ans = Math.max(ans, sum);
			return;
		}
		
		dfs( cal( sum , nums[index+1] , opers[index] ) ,index + 1);
		
		if(index + 1 < num_len-1) {
			int now_sum = cal(nums[index+1] , nums[index+2], opers[index+1]);
			
			dfs( cal( sum, now_sum, opers[index]) , index + 2);
		}
	}
	
	private static int cal(int n1, int n2, char op){
		if(op == '+') return n1+n2;
		else if(op == '-') return n1-n2;
		else return n1*n2;
	}
	
}
