import java.util.Arrays;
import java.util.Scanner;

public class Q1107 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
	
		int k = sc.nextInt();
		int N = sc.nextInt();
		
		int[] cantUse = new int[N];
		for(int i=0; i<N; i++) {
			cantUse[i] = sc.nextInt();
		}
				
		int nearest = 100;
		int nearest_c = 0;
		
		for(int i = 0; i <= 500000; i++) {
			int left = k-i;
			int right = k+i;
			
			boolean imposs = false;
			
			if(left >= 0) {
				String l = String.valueOf(left);
				A : for(int x=0; x<l.length(); x++) {
					for(int y=0; y<N; y++) {
						if(l.charAt(x) == (cantUse[y] + '0')) {	// 불가한 경우
							imposs = true;
							break A;
						}
					}
				}
				
				if(!imposs) {
					nearest = left;
					nearest_c = l.length();
					break;
				}
			}
			
			imposs = false;
			String r = String.valueOf(right);
			A : for(int x=0; x<r.length(); x++) {
				for(int y=0; y<N; y++) {
					if(r.charAt(x) == (cantUse[y] + '0')) {	// 불가한 경우
						imposs = true;
						break A;
					}
				}
			}
			
			if(!imposs) {
				nearest = right;
				nearest_c = r.length();
				break;
			}
			
		}
		
		
		int ans = Math.abs(100 - k);
		ans = Math.min(ans, nearest_c + Math.abs(nearest - k));
		
		//System.out.println(nearest_c);
		//System.out.println(Math.abs(nearest - k));
		
		System.out.println(ans);
		
	}
}