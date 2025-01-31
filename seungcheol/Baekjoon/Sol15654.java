
import java.util.Arrays;
import java.util.Scanner;


public class Sol15654 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);

        perm(arr, 0, new int[m], new boolean[n]);

        

    }

    static void perm(int[] arr, int depth,int[] sel, boolean[] v){
        if(depth == sel.length){
            for(int k = 0; k < sel.length; k++){
                System.out.print(sel[k]+" ");
            }
            System.out.println();
            return;
        }


        for(int i = 0; i < arr.length; i++){
            if(!v[i]){
                v[i] = true;
                sel[depth] = arr[i];
                perm(arr, depth+1, sel, v);
                v[i] = false;
            }
        }
    }
}
