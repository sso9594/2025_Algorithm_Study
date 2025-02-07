import java.util.Scanner;

public class Solution17070 {
    static int N;
    static int[][] home;
    static int cnt = 0;

    public static void dfs(int a, int b, int c, int d) {
        if (c == N-1 && d == N-1) {
            cnt += 1;
            return;
        }

        if (c-a == 0 && d-b == 1) {
            if (d+1 < N && home[c][d+1] == 0) {
                dfs(c, d, c, d+1);
            }
            if (c+1 < N && d+1 < N && home[c][d+1] == 0 && home[c+1][d+1] == 0 && home[c+1][d] == 0) {
                dfs(c, d, c+1, d+1);
            }
        } else if (c-a == 1 && d-b == 0) {
            if (c+1 < N && home[c+1][d] == 0) {
                dfs(c, d, c+1, d);
            }
            if (c+1 < N && d+1 < N && home[c][d+1] == 0 && home[c+1][d+1] == 0 && home[c+1][d] == 0) {
                dfs(c, d, c+1, d+1);
            }
        } else if (c-a == 1 && d-b == 1) {
            if (d+1 < N && home[c][d+1] == 0) {
                dfs(c, d, c, d+1);
            }
            if (c+1 < N && home[c+1][d] == 0) {
                dfs(c, d, c+1, d);
            }
            if (c+1 < N && d+1 < N && home[c][d+1] == 0 && home[c+1][d+1] == 0 && home[c+1][d] == 0) {
                dfs(c, d, c+1, d+1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        home = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                home[i][j] = sc.nextInt();
            }
        }
        dfs(0, 0, 0, 1);
        System.out.println(cnt);
    }
}
