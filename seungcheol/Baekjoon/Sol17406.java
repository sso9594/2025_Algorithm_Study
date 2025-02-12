
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sol17406 {
    static int[][] map;
    static int[][] roArr;
    static List<Integer[][]> roPerm = new ArrayList<>();
    static int minResult = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // map 입력
        int[][] originMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                originMap[i][j] = sc.nextInt();
            }
        }
        
        // 회전 연산 정보 저장
        roArr = new int[k][3];
        for (int i = 0; i < k; i++) {
            roArr[i][0] = sc.nextInt();
            roArr[i][1] = sc.nextInt();
            roArr[i][2] = sc.nextInt();
        }

        // 회전 연산 정보 순열 구하기
        perm(0, new int[k][3], new boolean[k]);

        for (Integer[][] roInfos : roPerm) {
            //이동 시킬 맵 복사
            map = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] =originMap[i][j];
                }
            }

            // 회전 연산 정보 불러오기
            for (int i = 0; i < k; i++) {
                int top = roInfos[i][0] - roInfos[i][2] - 1;
                int left = roInfos[i][1] - roInfos[i][2] - 1;
                int bottom = roInfos[i][0] + roInfos[i][2] - 1;
                int right = roInfos[i][1] + roInfos[i][2] - 1;

                int pre = map[bottom][left];

                int layerCount = Math.min(bottom - top, right - left) / 2;

                // 한 레이어 씩 돌리기
                for (int l = 0; l < layerCount; l++) {
                    int temp;

                    // 배열 한 바꾸 돌리기
                    for (int q = bottom; q > top; q--) {// 아래에서 위로
                        temp = map[q][left];
                        map[q][left] = pre;
                        pre = temp;
                    }
                    for (int q = left; q < right; q++) {// 왼쪽에서 오른쪽
                        temp = map[top][q];
                        map[top][q] = pre;
                        pre = temp;
                    }
                    for (int q = top; q < bottom; q++) {// 위에서 아래로
                        temp = map[q][right];
                        map[q][right] = pre;
                        pre = temp;
                    }
                    for (int q = right; q > left; q--) {// 오른쪽에서 왼쪽
                        temp = map[bottom][q];
                        map[bottom][q] = pre;
                        pre = temp;
                    }
                    map[bottom][left] = pre;

                    // 안쪽 레이어로
                    top++;
                    left++;
                    bottom--;
                    right--;
                }

                
            }
            for (int q = 0; q < n; q++) {
                int temp = 0;
                for(int p = 0; p < m; p++){
                    temp += map[q][p];
                }
                if(temp < minResult){
                    minResult = temp;
                }
            }
        }
        System.out.println(minResult);


    }

    static void perm(int depth, int[][] sel, boolean[] v) {
        // basis
        if (depth == roArr.length) {
            Integer[][] temp = new Integer[roArr.length][3];
            for (int i = 0; i < roArr.length; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[i][j] = sel[i][j];
                }
            }
            roPerm.add(temp);
            return;
        }

        // inductive
        for (int i = 0; i < roArr.length; i++) {
            if (!v[i]) {
                v[i] = true;
                sel[depth] = roArr[i];
                perm(depth + 1, sel, v);
                v[i] = false;
            }

        }
    }
}