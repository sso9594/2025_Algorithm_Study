import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Sol1874 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        List<Character> result = new ArrayList<>();
        int n = sc.nextInt();

        int[] arr = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        int idx = 0;
        int value = 1;

        stack.push(value);
        result.add('+');
        value++;

        boolean check = true;

        while (idx < n) {
            if (!stack.isEmpty() && stack.peek() == arr[idx]) {
                stack.pop();
                result.add('-');
                idx++;
            } else {
                if (value > n) {
                    System.out.println("NO");
                    return;
                }
                stack.push(value);
                result.add('+');
                value++;
            }
        }

        if (check) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

    }

}