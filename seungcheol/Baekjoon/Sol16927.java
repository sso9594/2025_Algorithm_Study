import java.util.Scanner;

public class Sol16927 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();

        // map 초기화
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // init
        int sx = 0;
        int sy = 0;
        int top = 0;
        int right = m-1;
        int bottom = n-1;
        int left = 0;

        // System.out.println();

        // 레이어 돌기
        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            int layerCount = (bottom+right-top-left) * 2;
            // 레이어 회전 몇번
            for (int j = 0; j < r % layerCount; j++) {

                int pre = map[sx][sy];
                // 레이어 한바꾸 돌기
                for ( int k = top ;k < bottom; k++) {// 아래로
                    // System.out.print(map[k][left]+" ");
                    int temp = map[k][left];
                    map[k][left] = pre;
                    pre = temp;
                }
                for (int k = left ;k < right; k++) {// 오른쪽
                    // System.out.print(map[bottom][k]+" ");
                    int temp = map[bottom][k];
                    map[bottom][k] = pre;
                    pre = temp;
                }
                for (int k = bottom ;k > top; k--) {// 위로
                    // System.out.print(map[k][right]+" ");
                    int temp = map[k][right];
                    map[k][right] = pre;
                    pre = temp;
                }
                for (int k = right ;k >= left; k--) {// 왼쪽
                    // System.out.print(map[top][k]+" ");
                    int temp = map[top][k];
                    map[top][k] = pre;
                    pre = temp;
                }
            }

            //레이어 안쪽으로 이동
            sx++;
            sy++;
            top++;
            left++;
            right--;
            bottom--;
        }


        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}