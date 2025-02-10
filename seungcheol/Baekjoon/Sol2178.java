import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sol2178 {
    public static void main(String[] args) {
        // Scanner를 통해 입력 받음
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 행의 수
        int m = sc.nextInt(); // 열의 수

        // n x m 크기의 2차원 배열 map 생성
        int[][] map = new int[n][m];
        
        // 각 행마다 문자열 형태로 입력을 받아서, 각 문자(숫자)를 정수로 변환하여 map에 저장
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                // '0'의 아스키 코드 48을 빼서 정수 값으로 변환
                map[i][j] = (int) line.charAt(j) - 48;
            }
        }

        // BFS(너비 우선 탐색)에 사용할 큐 생성 (각 원소는 [x, y] 좌표)
        Queue<Integer[]> queue = new LinkedList<>();
        // 방문 여부 체크를 위한 2차원 boolean 배열
        boolean[][] v = new boolean[n][m];
        // 각 좌표까지 도달하는 데 걸린 이동 횟수(레벨)를 저장할 배열
        int[][] l = new int[n][m];
        
        // 시작 좌표 (0,0)를 큐에 추가하고, 해당 위치의 이동 횟수는 1로 설정
        Integer[] start = new Integer[]{0, 0};
        queue.add(start);
        l[0][0] = 1;    
        
        // 큐가 빌 때까지 BFS 수행
        while (!queue.isEmpty()) {
            // 큐에서 좌표 하나를 꺼냄
            Integer[] xy = queue.poll();
            int x = xy[0];
            int y = xy[1];

            // 목표 지점 (n-1, m-1)에 도달하면 while문 탈출
            if (x == n - 1 && y == m - 1) break;

            // 상하좌우 이동을 위한 방향 배열 설정
            int[] dx = {0, -1, 0, 1}; // x축 이동량
            int[] dy = {1, 0, -1, 0}; // y축 이동량

            // 현재 위치까지의 이동 횟수를 가져옴
            int level = l[x][y];

            // 현재 위치가 아직 방문되지 않았다면
            if (v[x][y] == false) {
                // 현재 위치 방문 처리
                v[x][y] = true;
                // 네 방향에 대해 반복
                for (int i = 0; i < 4; i++) {
                    int xdx = x + dx[i]; // 새로운 x 좌표
                    int ydy = y + dy[i]; // 새로운 y 좌표
                    // 새 좌표가 맵의 범위 내에 있고, 이동 가능한 칸(값이 1)이며, 아직 방문하지 않았다면
                    if (xdx >= 0 && ydy >= 0 && xdx < n && ydy < m && map[xdx][ydy] == 1 && v[xdx][ydy] == false) {
                        // 이동 횟수를 현재 위치의 level + 1로 업데이트
                        l[xdx][ydy] = level + 1;
                        // 새 좌표를 큐에 추가하여 BFS 진행
                        queue.add(new Integer[]{xdx, ydy});
                    }
                }
            }
        }

        // // l 배열을 출력하여 각 좌표까지 도달하는 이동 횟수 확인 (디버깅용)
        // for (int i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(l[i]));
        // }
        
        // 목표 지점 (n-1, m-1)까지 도달하는 최소 이동 횟수 출력
        System.out.println(l[n - 1][m - 1]);
    }
}
