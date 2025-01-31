import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Sol16922 {
    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numArr = new int[]{1,5,10,50}; 

        int n = sc.nextInt();

        perm(numArr, 0, new int[n]);
        System.out.println(set.size());

        
    }

    static void perm(int[] arr, int depth, int[] sel){
        
        if(depth == sel.length){
            int sum = 0; 
            for(int j =0; j < sel.length; j++){
                sum += sel[j];
            }
            set.add(sum);

            return;
        }

        //중복 순열
        for(int i = arr.length-1; i >= 0; i--){
            sel[depth] = arr[i];
            perm(arr, depth+1, sel);
            arr = Arrays.copyOfRange(arr, 0, arr.length-1);
        }
        
    }
}
