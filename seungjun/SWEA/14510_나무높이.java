import java.util.*;

class Solution {
    static int[] dp;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();

        for (int tc=1; tc<=T; tc++) {
            int N = input.nextInt();
            int[] trees = new int[N];
            int taller_tree = Integer.MIN_VALUE;

            for (int n=0; n<N; n++) {
                trees[n] = input.nextInt();
                // 가장 큰 나무 높이 구하기
                if (trees[n] > taller_tree) {
                    taller_tree = trees[n];
                }
            }

            int sum_num = 0, odd_cnt = 0;
            for (int tree : trees) {
                int diff_height = taller_tree - tree;
                sum_num += diff_height; // 차이 값 모두 더하기

                // 차이값이 홀수 높이인 경우 카운트
                if (diff_height % 2 == 1) odd_cnt++;
            }

            // 물주는 날 계산
            dp = new int[sum_num+1];
            int odd_num = 0;
            for (int i=1; i<=sum_num; i++) {
                if (i % 3 == 2) dp[i] = i-odd_num++;
                else dp[i] = i-odd_num;
            }

            // 계산된 날에서 1의 개수
            int one = dp[sum_num] / 2 + dp[sum_num] % 2, result;
            // 1의 개수가 차이값이 홀수인 나무 갯수 이상 : 필요한 1의 개수 충족
            if (odd_cnt <= one) result = dp[sum_num];
            // 1의 개수가 차이값이 홀수인 나무 갯수 미만 : 홀수인 나무 갯수가 충족되는 날 계산
            else result = 2 * odd_cnt - 1;
            System.out.printf("#%d %d\n", tc, result);
        }
        input.close();
    }
}