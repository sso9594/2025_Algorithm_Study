import java.util.Scanner;
import java.util.Stack;
 
class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
 
        int T = input.nextInt();
        char[] sticks;
        Stack<Character> stack;
 
        for (int tc=1; tc<=T; tc++) {
            sticks = input.next().toCharArray();
            stack = new Stack<>();
            int cnt=0;
 
            for (int i=0; i<sticks.length; i++) {
                if (sticks[i] == '(') {
                    stack.push(sticks[i]);
                }
                else {
                    stack.pop();
                    if (sticks[i-1] == '(') {
                        cnt += stack.size();
                    }
                    else {
                        cnt++;
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, cnt);
        }
    }
}
