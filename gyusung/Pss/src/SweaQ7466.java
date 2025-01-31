import java.util.Arrays;
import java.util.Scanner;

public class SweaQ7466 {

	static int result, N;
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int k = sc.nextInt();
		
		result = 1;
		
		getGcd(k);
		
		System.out.println(result);
	}

	private static void getGcd(int k) {
		// TODO Auto-generated method stub
		if(k == 1) return;
		
		for(int i=N; i>1 ; i--) {
			if(k % i == 0) {
				result *= i;
				
				if(k < i-1) N = k;
				else N = i-1;
				
				getGcd(k/i);
				break;
			}
		}
		
	}

}
