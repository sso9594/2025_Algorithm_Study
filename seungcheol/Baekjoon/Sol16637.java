import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Sol16637 {
    static int maxValue = Integer.MIN_VALUE;
    //오퍼레이터 저장 객체
    static class Operator{
        int x;
        int y;
        char operator;
        Operator(int x, int y, char operator){
            this.x = x;
            this.y = y;
            this.operator = operator;
        }
        @Override
        public String toString() {
            return x+" "+y+" "+operator;
        }

    }

    public static void main(String[] args) {
        //데이터 입력
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n == 1){
            System.out.println(sc.nextInt());
            return;
        }
        String line = sc.next();
        //데이터에서 숫자와 연산자 분리
        int[] nums = new int[n/2+1];
        Operator[] operators = new Operator[(n/2)];

        int count = 0;
        for(int i = 0; i< line.length(); i+=2){
            nums[count] = (int) line.charAt(i) -48;
            count++;
        }
        
        count = 0;
        for(int i = 1; i< line.length(); i+=2){
            int x = count;
            int y = count+1;
            char oper = line.charAt(i);
            operators[count] = new Operator(x, y, oper);
            count++;
        }

        // 연산자에 따라 나올 수 있는 powerSet 반환
        for(int i = 0; i < operators.length;i++){
            powerSet(operators, nums, new Operator[i], 0, new boolean[operators.length], new boolean[nums.length],0);
        }

        System.out.println(maxValue);
        
    }
    static void powerSet(Operator[] operators,int[] nums, Operator[] sel, int depth, boolean[] v, boolean[] operV,int start){
        //basis
        if(depth == sel.length){
            //남은 계산들 저장
            int count = 0;
            List<Integer> result = new ArrayList<>();
            List<Character> resultOper = new ArrayList<>();

            for(int i = 0; i < operV.length; i++){
                if(operV[i] == true){
                    int x = sel[count].x;
                    int y = sel[count].y;
                    char operator = sel[count].operator;
                    int equal =0 ;
                    if(operator == '+'){
                        equal = nums[x]+nums[y];
                    }else if(operator == '-'){
                        equal = nums[x]-nums[y];
                    }else if(operator == '*'){
                        equal = nums[x]*nums[y];
                    }
                    result.add(Integer.valueOf(equal));
                    i++;
                    count++;
                }else{
                    result.add(nums[i]);
                }
            }
            count = 0;
            for(int i = 0; i < v.length; i++){
                if(v[i] == false){
                    resultOper.add(operators[i].operator);
                    count++;
                }
            }
            // System.out.println(Arrays.toString(result.toArray()));
            // System.out.println(Arrays.toString(resultOper.toArray()));
            //나머지 계산
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(result.get(0));
            result.remove(0);
            stack.push(result.get(0));
            result.remove(0);
            int total = 0;
            while(!resultOper.isEmpty()){
                if(stack.size() == 2){
                    int oper = resultOper.get(0);
                    resultOper.remove(0);
                    int b = stack.pop();
                    int a = stack.pop();
                    int operResult = 0;
                    if(oper == '+'){
                        operResult = a+b;
                    }else if(oper == '-'){
                        operResult = a-b;
                    }else if(oper == '*'){
                        operResult = a*b;
                    }
                    stack.push(operResult);
                    total = operResult;
                }else{
                    stack.push(result.get(0));
                    result.remove(0);
                }
            }
            if(maxValue < total){
                maxValue = total;
            }
            return;
        }


        //inductive
        for(int i = start; i < operators.length; i++){
            if(!v[i] && !operV[operators[i].x] && !operV[operators[i].y]){
                v[i] = true;
                sel[depth] = operators[i];
                operV[operators[i].x] = true;
                operV[operators[i].y] = true;
                powerSet(operators,nums, sel,depth+1, v, operV,i+1);
                v[i] = false;
                operV[operators[i].x] = false;
                operV[operators[i].y] = false;
            }
        }
    }
}
