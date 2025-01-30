
import java.util.Scanner;

public class Sol14503 {

    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();
        int d = sc.nextInt();

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        //사방 탐색 이동 차
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (true) {
            //현재 칸이 청소되지 않은 경우, 현재 칸 청소
            if (map[x][y] == 0) {
                map[x][y] = 2;
                result++;
            }

            //청소 되지 않은 빈칸이 있는지 확인
            boolean check = false;
            for (int i = 0; i < 4; i++) {
                int xdx = x + dx[i];
                int ydy = y + dy[i];
                if (xdx >= 0 && xdx < n && ydy >= 0 && ydy < m && map[xdx][ydy] == 0) {
                    check = true;
                }
            }
            //바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
            if (check == false) {
                int xdx;
                int ydy;
                if (d == 0) {
                    xdx = x + 1;
                    ydy = y;
                } else if (d == 1) {
                    xdx = x;
                    ydy = y - 1;
                } else if (d == 2) {
                    xdx = x - 1;
                    ydy = y;
                } else {
                    xdx = x;
                    ydy = y + 1;
                }
                //뒤쪽이 map 안인 경우
                if (xdx >= 0 && xdx < n && ydy >= 0 && ydy < m) {
                    //뒤가 벽인 경우
                    if (map[xdx][ydy] == 1) {
                        break; 
                    }//뒤로 갈 수 있는 경우
                    else {
                        x = xdx;
                        y = ydy;
                        continue;
                    }
                    //뒤쪽이 map 밖인 경우
                } else {
                    break;
                }
            } //현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            else {
                //반시계 방향으로 90 회전한다.
                d -= 1;
                if (d == -1) {
                    d = 3;
                }
                int xdx;
                int ydy;
                if (d == 0) {
                    xdx = x - 1;
                    ydy = y;
                } else if (d == 1) {
                    xdx = x;
                    ydy = y + 1;
                } else if (d == 2) {
                    xdx = x + 1;
                    ydy = y;
                } else {
                    xdx = x;
                    ydy = y - 1;
                }
                //앞이 map 안인 경우
                if (xdx >= 0 && xdx < n && ydy >= 0 && ydy < m) {
                    //바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다
                    if(map[xdx][ydy] == 0){
                        x = xdx;
                        y = ydy;
                    } 
                }

            }

        }
    System.out.println(result);
    }

}
