import java.util.Scanner;
import java.util.Stack;

public class Q17298 {

public static void main(String[] args) {
    // TODO Auto-generated method stub

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int[] arr = new int[N];
    int[] ans = new int[N];

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    for(int i =0; i<N; i++) {
        arr[i] = sc.nextInt();
    }

    for(int i = N-1; i>=0; i--) {

        if(stack1.isEmpty()) {
            ans[i] = -1;
        }else {
            while(!stack1.isEmpty()) {
                if(arr[i] < stack1.peek()) {
                    ans[i] = stack1.peek();
                    break;
                }else {
                    stack2.push(stack1.pop());
                }
                
                if(stack1.isEmpty()) {
                    ans[i] = -1;
                }
            }
        }
        
        
        for(int j=0; j<stack2.size(); j++) {
            stack1.push(stack2.pop());
        }
        stack1.push(arr[i]);

    }

    StringBuilder sb = new StringBuilder();
    for(int i =0; i<N; i++) {
        sb.append(ans[i] + " ");
    }
    
    System.out.println(sb);
}
}