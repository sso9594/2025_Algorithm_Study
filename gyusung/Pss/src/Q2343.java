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
			right += arr[i];
		}
		
		while(left < right) {
			
			int mid = (left + right) / 2;
			int num = 0;
			int sum = 0;
		//	System.out.println("mid " + mid);
			
			// 전체 배열을 돌며 합산
			for(int i =0; i<N-1; i++) {
				if(sum + arr[i+1] >= mid) {
					num++;
					sum = arr[i+1];
			//		System.out.print(arr[i] + " ");
				}
				else sum += arr[i+1 ];
			}
			if(sum > mid) {
				//System.out.println(arr[N-1]);
				num++;
			}
			
			if(num > M) { // 타겟넘버가 너무 작은 경우
			//	System.out.println("mid가 작음");
			//	System.out.println("tar : " + mid + " num : " + num);
				left = mid + 1;
			}else { 	//타겟넘버가 큰 경우
			//	System.out.println("mid가 큼");
			//	System.out.println("tar : " + mid + " num : " + num);
				right = mid;
			}
		}
		
		System.out.println(left);
	}
}