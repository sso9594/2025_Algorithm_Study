import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sol1935 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String line = sc.next();
        Map<Character, Double> dict = new HashMap<>();
        for(int i = 'A'; i < 'A'+n; i++){
            dict.put((char) i,sc.nextDouble());
        }

        List<Double> stack = new ArrayList<>();
        
        for(char c : line.toCharArray()){
            double a, b;

            if(c >= 'A' && c <= 'Z'){
                stack.add(dict.get(c));
            }else{
                b = stack.get(stack.size()-1);
                stack.remove(stack.size()-1);
                a = stack.get(stack.size()-1);
                stack.remove(stack.size()-1);
                
                if(c == '*'){
                    stack.add(a*b);
                }else if(c == '+'){
                    stack.add(a+b);
                }else if(c == '/'){
                    stack.add(a/b);
                }else if(c == '-'){
                    stack.add(a-b);
                }
            }
        }
        System.out.printf("%.2f",stack.get(0));

    }
}
