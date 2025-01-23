import java.util.Scanner;
public class Q2343 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr = new int[N];
		int left = 0;
		int right = 0;
		
		for(int i =0; i<N; i++) {
			arr[i] = sc.nextInt();
			left = Math.max(left, arr[i]);
			right += arr[i];
		}
		
		while(left < right) {
			
			int mid = (left + right) / 2;
			int cnt = 0;							// 조각 수
			int total = arr[0];
			//System.out.print("mid " + mid + " :");
			
			for(int i =0; i<N-1; i++) {
				if(total + arr[i+1] > mid) {		//잘리는 위치
					cnt++;
					total = arr[i+1];
				//	System.out.print(arr[i] + " ");
				}else {
					total += arr[i+1];
				}
			}
			
			//System.out.println();
			
			if(cnt + 1 > M) { 	// 너무 많은 블루레이 개수가 나옴 --> 타겟넘버가 너무 작은 경우
				left = mid + 1;
			}else { 			//타겟넘버가 큰 경우
				right = mid;
			}
		}
		
		System.out.println(left);
	}
}