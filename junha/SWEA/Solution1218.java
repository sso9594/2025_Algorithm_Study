import java.util.*;

public class Solution1218 {
    static int N;
    static char[] pArr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int t = 1; t <= 10; t++) {
            N = sc.nextInt();
            pArr = sc.next().toCharArray();
            Stack<Character> stack = new Stack<>();
            int answer = 0;
            boolean success = true;

            for (int i = 0; i < N; i++) {
                char c = pArr[i];
                stack.push(c);

                if (c == ')') {
                    stack.pop();
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        success = false;
                        break;
                    }
                } else if (c == ']') {
                    stack.pop();
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        success = false;
                        break;
                    }
                } else if (c == '}') {
                    stack.pop();
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        success = false;
                        break;
                    }
                } else if (c == '>') {
                    stack.pop();
                    if (stack.peek() == '<') {
                        stack.pop();
                    } else {
                        success = false;
                        break;
                    }
                }
            }

            if (stack.isEmpty() && success) {
                answer = 1;
            }

            System.out.printf("#%d %d\n", t, answer);
        }
    }
}