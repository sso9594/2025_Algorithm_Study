import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        
        int[] line = new int[k];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < k; i++){
            line[i] = sc.nextInt();
            if(max < line[i]){
                max = line[i];
            }
        }

        long maxLen = 0;
        long startP = 1;
        long endP = max;
        while(startP <= endP){
            long mid = (startP+endP)/2;

            long len = 0;    
            for(int i = 0; i < k; i++){
                len += line[i]/mid;
            }

            if(len >= n){
                startP = mid+1;
                maxLen = mid;
            }else{
                endP = mid-1;
            }
        }
        

        System.out.println(maxLen);
    }
}