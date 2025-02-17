import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;


class Q14510{
   	public static int[] trees;
	public static int N;
    
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
	int N = sc.nextInt();
		trees = new int[N];
		
		for(int i = 0; i<N; i++) {
			trees[i] = sc.nextInt();
		}
		
	int max = Arrays.stream(trees).max().getAsInt();
		int to_make_1 = 0;
		int to_make_2 = 0;
		
		for(int i = 0; i<N; i++) {
			int now_diff = max-trees[i];
			
			if(now_diff >= 3) {
				to_make_1 += now_diff % 2;
				to_make_2 += now_diff / 2;
			}else if(now_diff == 2) {
				to_make_2++;
			}else if(now_diff == 1) {
				to_make_1++;
			}else continue;
		}

		if(to_make_2 > to_make_1) {			
			while(to_make_2 - to_make_1 > 1) {
				to_make_1 += 2;
				to_make_2 -= 1;
			}
		}
		
		//System.out.println(to_make_1 + " " + to_make_2);
		
		int ans = 0;
				
		if(to_make_1 > to_make_2) {
			ans = to_make_1 * 2 - 1;
		}else if(to_make_2 > to_make_1) {
			ans = to_make_2 * 2;
		}else{
			ans = to_make_1 + to_make_2;
		}
		
            
			System.out.println("#" + test_case + " " + ans);
		}
	}
}