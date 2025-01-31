import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;

class SweaQ7465{
	public static void main(String args[]) throws Exception{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++){

			int N = sc.nextInt();
			int M = sc.nextInt();

			int[] arr = new int[N+1];
			Arrays.fill(arr, Integer.MAX_VALUE);

			for(int i=0; i<M; i++){
				int a = sc.nextInt();
				int b = sc.nextInt();

				//System.out.println(a +" " + b);

				if(arr[a] == Integer.MAX_VALUE && arr[b] == Integer.MAX_VALUE) arr[a] = arr[b] = Math.min(a, b);
				else if(arr[a] == Integer.MAX_VALUE) arr[a] = Math.min(arr[b], a);
				else if(arr[b] == Integer.MAX_VALUE) arr[b] = Math.min(arr[a], b);
				else {
					if(a > b) {
						int tmp = arr[b];
						arr[b] = arr[a];
						for(int j =0; j<=N; j++) {
							if(arr[j] == tmp) arr[j] = arr[a];
						}
					}else {
						int tmp = arr[a];
						arr[a] = arr[b];
						for(int j =0; j<=N; j++) {
							if(arr[j] == tmp) arr[j] = arr[b];
						}
					}
				}

				//System.out.println(Arrays.toString(arr));
			}

			HashSet<Integer> set = new HashSet<>();
			int size = 0;

			for(int i = 1; i<=N; i++) {
				if(arr[i] < Integer.MAX_VALUE) {
					if(!set.contains(arr[i])) {
						set.add(arr[i]);
						size++;
					}
				}

				else size++;
			}
			System.out.println("#" + test_case + " " + size);

		}
	}
}