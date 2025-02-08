
import java.util.Scanner;


public class Sol16926 {
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }
        
        boolean[][] v = new boolean[n][m];
        int d= 0; //0아래, 1 오른쪽, 2 위, 3 왼쪽
        int startX = 0;
        int startY = 0;

        for(int i = 0; i < (int) n/2; i++) {
            for(int j = 0; j < r; j++) {
                int cx = startX;
                int cy = startY;
                int temp;
                int cValue = map[cx][cy];
                
                while(true) {
                    int nx = cx;
                    int ny = cy;
                    
                    if(d == 0) { //아래
                        nx++;
                        if(nx >= 0 && nx < n && !v[nx][ny]) {
                            if(j == r-1) v[nx][ny] = true;
                            
                            temp = map[nx][ny];
                            map[nx][ny] = cValue;
                            cValue = temp;
                            cx = nx;
                            cy = ny;
                        } else {
                            d = (d + 1) % 4;
                        }
                    } else if(d == 1) { // 오른쪽
                        ny++;
                        if(ny >= 0 && ny < m && !v[nx][ny]) {
                            if(j == r-1) v[nx][ny] = true;
                            temp = map[nx][ny];
                            map[nx][ny] = cValue;
                            cValue = temp;
                            cx = nx;
                            cy = ny;
                        } else {
                            d = (d + 1) % 4;
                        }
                    } else if(d == 2) { // 위
                        nx--;
                        if(nx >= 0 && nx < n && !v[nx][ny]) {
                            if(j == r-1) v[nx][ny] = true;
                            temp = map[nx][ny];
                            map[nx][ny] = cValue;
                            cValue = temp;
                            cx = nx;
                            cy = ny;
                        } else {
                            d = (d + 1) % 4;
                        }
                    } else if(d == 3) { // 왼쪽
                        ny--;
                        if(ny >= 0 && ny < m && !v[nx][ny]) {
                            if(j == r-1) v[nx][ny] = true;
                            temp = map[nx][ny];
                            map[nx][ny] = cValue;
                            cValue = temp;
                            cx = nx;
                            cy = ny;
                        } else {
                            d = (d + 1) % 4;
                        }
                    }
                    
                    if(nx == startX && ny == startY) {
                        break;
                    }
                }
            }
            startX++;
            startY++;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j< m; j++){
                System.out.print(map[i][j] +" ");
            }
            System.out.println();
        }


        
    }

    
}
