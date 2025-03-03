import java.util.Scanner;

class Sol2193{
    public static void Sol2193(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        if(n == 1){
            System.out.println(1);
        }else{
            long[] dp = new long[n];
            dp[0] = 1;
            dp[1] = 1;
            recursive(dp, 2);
        }

    }

    static void recursive(long[] dp, int idx){
        if(idx == dp.length){
            System.out.println(dp[idx-1]);
            return;
        }

        dp[idx] = dp[idx-1] + dp[idx-2];
        recursive(dp, idx+1);
    }

}