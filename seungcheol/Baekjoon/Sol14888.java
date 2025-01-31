
import java.util.Scanner;




public class Sol14888{
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }

        int[] operators = new int[4];
        int operNum = 0;
        for(int i = 0; i < 4; i++){
            operators[i] = sc.nextInt();
            operNum += operators[i];
        }

        int[] operArr = new int[operNum];
        int idx = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < operators[i]; j++){
                operArr[idx] = i;
                idx++;
            }
        }

        perm(nums, operArr, new int[operArr.length], 0, new boolean[operArr.length]);
        
        System.out.printf("%d\n%d\n",max,min);
    }

    static void perm(int[] nums, int[] arr, int[] sel, int depth, boolean[] v){
        if(sel.length == depth){
            int sum = nums[0];
            for(int idx = 0; idx < sel.length; idx++){
                if(sel[idx] == 0){
                    sum += nums[idx+1];
                }else if(sel[idx] == 1){
                    sum -= nums[idx+1];
                }else if(sel[idx] == 2){
                    sum *= nums[idx+1];
                }else{
                    int share = (int) (Math.abs(sum) / nums[idx+1]);
                    if(sum < 0){
                        sum = share - share - share;
                    }
                    else{
                        sum = share;
                    }
                }
            }
            if(max < sum){
                max = sum;
            }
            if(min > sum){
                min = sum;
            }
            return;
        }

        //inductive
        for(int i = 0; i < arr.length; i++){
            if(!v[i]){
                v[i] = true;
                sel[depth] = arr[i];
                perm(nums, arr, sel, depth+1, v);
                v[i] = false;
            }
        }
        
    }
}
