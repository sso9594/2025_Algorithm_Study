import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    static int N, A, B, C, D;
    static int[][] pool;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    static int swim(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j, 0});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            int time = p[2];

            if (x == C && y == D) {
                return time;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dr[d];
                int ny = y + dc[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {

                    if (pool[nx][ny] == 2) {
                        if (time % 3 != 2) {
                            queue.add(new int[]{x, y, time+1});
                        } else {
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny, time+1});
                        }
                    } else if (pool[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, time+1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            pool = new int[N][N];
            visited = new boolean[N][N];

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    pool[i][j] = sc.nextInt();
                }
            }

            A = sc.nextInt();
            B = sc.nextInt();
            C = sc.nextInt();
            D = sc.nextInt();

            System.out.printf("#%d %d\n", tc, swim(A, B));
        }
        sc.close();
    }
}