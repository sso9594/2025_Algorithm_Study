
import java.util.LinkedList;
import java.util.Scanner;

public class Sol2164 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //n 입력
        int n = sc.nextInt();
        LinkedList<Integer> arr = new LinkedList<>();
        for (int i = 1 ; i <= n; i++){
            arr.add(i);
        }
        while(true){
            // 탈출 조건
            if(arr.size() == 1){
                break;
            }

            arr.remove(0);
            arr.add(arr.get(0));
            arr.remove(0);

        }

        System.out.println(arr.get(0));

    }
}