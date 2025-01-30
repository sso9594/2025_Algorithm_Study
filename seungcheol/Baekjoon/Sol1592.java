import java.util.Scanner;

public class Sol1592 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n,m,l;
        n = sc.nextInt();
        m = sc.nextInt();
        l = sc.nextInt();

        int[] line = new int[n];
        for (int i = 0; i < n; i++){
            line[i] = 0;
        }

        int targetIdx = 0;
        int result = 0;

        while(true){
            line[targetIdx]++;
            if(line[targetIdx] >= m){
                break;
            }
            result++;
            //홀짝 구분
            if(line[targetIdx] % 2 ==0){
                targetIdx = (targetIdx + l) % n;
            }else{
                targetIdx -= l;
                if (targetIdx < 0){
                    targetIdx = n + targetIdx;
                }
            }
        }

        System.out.println(result);

    }
}