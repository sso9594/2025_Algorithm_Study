import java.util.Scanner;

public class Q9934 {
	
	public static int[] arr;
	public static int[][] ans;
	public static int last_len;
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		arr= new int[(int) Math.pow(2, N) - 1];
		last_len = (int) Math.pow(2, N-1);
		ans = new int[N][last_len];
		
		for(int i =0; i<arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		half(0, arr.length, 0);
	
		A:for(int i =0; i<N; i++){
			for(int j=0; j<last_len; j++) {
				if(ans[i][j] != 0) {
					System.out.print(ans[i][j] + " ");
				}else {
					System.out.println();
					continue A;
				}
			}
		}
		
		
	}
	
	public static void half(int start, int end,int level) {
		if(end - start < 1) return;
		
		int mid = (start + end) / 2;
		
		for(int i=0; i<last_len; i++) {
			if(ans[level][i] == 0) {
				ans[level][i] = arr[mid];
				break;
			}
		}
		
		half(start, mid, level+1);
		half(mid+1, end, level+1);		
	}
}