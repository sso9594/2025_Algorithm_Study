import java.util.Arrays;
import java.util.Scanner;

public class ATM_11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] arr = new int[N];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);

        int result = 0;

        for (int i = 0; i < N; i++) {
            sum = sum + arr[i];
            result += sum;
        }

        System.out.println(result);
    }
}