
import java.util.ArrayDeque;
import java.util.Scanner;


public class SSol1225 {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        
        for(int t = 1; t <= 10; t++){
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            sc.nextInt();
            for(int i = 0; i < 8; i++){
                queue.offer(sc.nextInt());
            }

            boolean check = true;
            while(true){
                if(!check) break;
                int n = 1;
                for(int i = 0; i < 5; i++){
                    int temp = queue.poll();
                    temp = temp-n-i;
                    if(temp <= 0){
                        temp = 0;
                        queue.offer(temp);
                        check = false;
                        break;
                    }
                    queue.offer(temp);
                }
            }

            System.out.print("#"+t+" ");
            for(int i = 0; i < 8; i++){
                System.out.print(queue.poll()+" ");
            }
            System.out.println();
        }


        
        
        

    }
}
