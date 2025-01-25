import java.util.Scanner;

public class Sol15649 {
        public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];

		for(int i = 1; i <= n; i++){
			arr[i-1] = i;
		}


		perm(arr, 0, new int[m], new boolean[n]);
    }

	static void perm(int[] arr, int idx, int[] sel, boolean[] v ){
		if(sel.length == idx){
			for(int i = 0; i < sel.length; i++){
				System.out.print(sel[i]+" ");
			}
			System.out.println();
			return;
		}

		
		for(int i = 0; i < arr.length; i++){
			if(v[i] == false){
				v[i] = true;
				sel[idx] = arr[i];
				perm(arr, idx+1, sel, v);
				v[i] = false;
			}
		}

	}
}
