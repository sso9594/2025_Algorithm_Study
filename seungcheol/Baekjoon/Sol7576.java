import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sol7576 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] map = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int days = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundNew = false;
            
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                
                // 사방 탐색
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) {
                        map[nx][ny] = 1; // 토마토 익힘
                        queue.offer(new int[]{nx, ny});
                        foundNew = true;
                    }
                }
            }

            if (foundNew) {
                days++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) { 
                    System.out.println(-1);
                    return;
                }
            }
        }
        
        System.out.println(days);
    }
}
