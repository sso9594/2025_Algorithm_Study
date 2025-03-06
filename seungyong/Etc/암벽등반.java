package seungyong.Etc;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class 암벽등반 {
    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();

            int startX = 0;
            int startY = 0;
            result = -1;

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j]==2){
                        startX = i;
                        startY = j;
                    }
                }
            }

            for (int i = 1; i < N; i++) {
                bfs(startX, startY, i);
                if(result!=-1) break;
            }

            System.out.println("#" + test_case + " " + result);
        }
    }

    static void bfs(int x, int y, int level){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        boolean[][] visited = new boolean[N][M];
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            if(map[currentX][currentY]==3){
                result = level;
                return;
            }
                
            // 위 아래 움직임
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= level; j++) {
                    int nextX = currentX + dx[i] * j;
                    int nextY = currentY + dy[i] * j;

                    if(nextX>=0 && nextY>=0 && nextX<N && nextY<M
                    && !visited[nextX][nextY] && map[nextX][nextY]!=0){
                        queue.offer(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            // 좌 우 움직임
            for (int i = 2; i < dx.length; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];

                if(nextX>=0 && nextY>=0 && nextX<N && nextY<M
                    && !visited[nextX][nextY] && map[nextX][nextY]!=0){
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }
}

// 3
// 5 8
// 1 1 1 1 0 0 0 0
// 0 0 0 3 0 1 1 1
// 1 1 1 0 0 1 0 0
// 0 0 0 0 0 0 1 0
// 2 1 1 1 1 1 1 1
// 5 6
// 0 1 1 1 0 0
// 3 1 0 1 1 0
// 0 0 0 0 1 1
// 0 0 0 0 0 1
// 2 1 1 1 1 1
// 9 13
// 0 1 1 1 1 1 1 1 1 1 1 1 1
// 1 1 0 0 0 0 0 0 0 0 0 1 1
// 0 0 0 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 0 0
// 1 1 0 0 0 0 0 0 0 0 0 1 3
// 0 0 0 0 0 0 0 0 0 0 0 0 0
// 1 1 0 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 0 0
// 2 1 1 1 1 1 1 1 1 1 1 1 1