import java.util.Arrays;
import java.util.Scanner;

class SSol1219
{
    static int result = 0; 
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            result = 0;
            int[] arr1 = new int[100];
            Arrays.fill(arr1, -1);
            int[] arr2 = new int[100];
            Arrays.fill(arr2, -1);

            int testNum = sc.nextInt();
            int n = sc.nextInt();

            for(int i = 0; i < n; i++){
                int idx = sc.nextInt();
                int val = sc.nextInt();

                if(arr1[idx] == -1) arr1[idx] = val;
                else arr2[idx] = val;
            }

            recursive(arr1, arr2, 0);
            System.out.println("#"+testNum +" "+ result);
		}

	}
    static void recursive(int[] arr1, int[] arr2,int idx){
        //basis
        if(arr1[idx] == 99 || arr2[idx] == 99){
            result = 1;
        }

        //inductive
        if(arr1[idx] != -1) recursive(arr1, arr2, arr1[idx]);
        if(arr2[idx] != -1) recursive(arr1, arr2, arr2[idx]);
    }
}