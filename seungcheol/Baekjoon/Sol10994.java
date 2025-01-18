//[Baekjoon-10994]: 별 찍기 - 19
import java.util.Scanner;

public class Sol10994 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int WH_LEN = 4*n-3;

        char[][] grap = new char[WH_LEN][WH_LEN];

        //그래프 초기화
        for (int i = 0; i < WH_LEN; i++) {
            for (int j = 0; j < WH_LEN; j++) {
                grap[i][j] = ' ';
            }
        }

        grap = drawRac(0, n, grap);

        //출력
        for (int i = 0; i < WH_LEN; i++) {
            for (int j = 0; j < WH_LEN; j++) {
                System.out.print(grap[i][j]);
            }
            System.out.println();
        }

    }

    public static char[][] drawRac(int startPoint, int n, char[][] grap) {
        int WH_LEN = 4*n-3;

        //탈출 조건
        if (n == 1) {
            grap[startPoint][startPoint] = '*';
            return grap;
        }

        for (int i = startPoint; i < startPoint + WH_LEN; i++) {
            for (int j = startPoint; j < startPoint + WH_LEN; j++) {
                if (i == startPoint || j == startPoint || i == startPoint + WH_LEN- 1 || j == startPoint + WH_LEN- 1) {
                    grap[i][j] = '*';
                }
            }
        }

        return drawRac(startPoint + 2, n - 1, grap);

    }

}
