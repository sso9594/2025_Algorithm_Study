import java.util.*;

public class Solution1913 {
    static int N;
    static int find;
    static int[][] snail;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        find = sc.nextInt();
        snail = new int[N][N];

        int x = N / 2;
        int y = N / 2;
        int num = 1;
        int k = 0;

        snail[x][y] = 1;
        num++;

        loop: while (num <= N * N) {
            k++;
            for (int j = 0; j < k; j++) {
                x--;
                if (x >= 0) {
                    snail[x][y] = num++;
                    if (num == N * N + 1)
                        break loop;
                }
            }
            for (int j = 0; j < k; j++) {
                y++;
                if (y < N) {
                    snail[x][y] = num++;
                }
            }

            k++;
            for (int j = 0; j < k; j++) {
                x++;
                if (x < N) {
                    snail[x][y] = num++;
                }
            }
            for (int j = 0; j < k; j++) {
                y--;
                if (y >= 0) {
                    snail[x][y] = num++;
                }
            }
        }

        for (int[] sn : snail) {
            for (int s : sn) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

        loop2: for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (snail[i][j] == find) {
                    System.out.println((i + 1) + " " + (j + 1));
                    break loop2;
                }
            }
        }

    }
}