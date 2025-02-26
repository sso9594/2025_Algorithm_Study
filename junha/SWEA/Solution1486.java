import java.util.*;

public class Solution1486 {
    static int T;
    static int N;
    static int B;
    static int[] heights;
    static int minSum;

    static void dfs(int idx, int start, int sum, int[] sel) {
        if (sum >= B) {
            minSum = Math.min(minSum, sum);
            return;
        }
        for (int i = start; i < N; i++) {
            sum += heights[i];
            sel[idx] = heights[i];
            dfs(idx + 1, i + 1, sum, sel);
            sum -= heights[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            B = sc.nextInt();
            heights = new int[N];

            for (int i = 0; i < N; i++) {
                heights[i] = sc.nextInt();
            }

            minSum = Integer.MAX_VALUE;
            dfs(0, 0, 0, new int[N]);

            System.out.printf("#%d %d\n", t, minSum - B);
        }
    }
}
