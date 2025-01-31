import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sol1244 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int[] swit = new int[k];
        for (int i = 0; i < k; i++) {
            swit[i] = sc.nextInt();
        }

        int sNum = sc.nextInt();
        int[][] students = new int[sNum][2];
        for (int i = 0; i < sNum; i++) {
            for (int j = 0; j < 2; j++) {
                students[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < sNum; i++) {
            int gender = students[i][0];
            int n = students[i][1] - 1;

            if (gender == 1) {
                for (int j = 1; (n + 1) * j - 1 < k; j++) {
                    int idx = (n + 1) * j - 1;

                    if (idx >= k) break;
                    if(swit[idx] == 1) swit[idx] = 0;
                    else swit[idx] = 1;
                }
            } else {
                int[] arr = findGirl(swit, n);
                for (int a : arr) {
                    if(swit[a] == 1) swit[a] = 0;
                    else swit[a] = 1;
                }
            }
        }

        for (int i = 0; i < swit.length; i++) {
            if (i != 0 && i % 20 == 0) {
                System.out.println();
            }
            System.out.print(swit[i] + " ");
        }
    }

    static int[] findGirl(int[] swit, int n) {
        List<Integer> list = new ArrayList<>();
        int start = n;
        int end = n;

        for (int i = 1; i < swit.length / 2; i++) {
            int left = n - i;
            int right = n + i;

            // 경계 조건 추가: 좌우 대칭 범위가 배열의 경계를 넘으면 중단
            if (left < 0 || right >= swit.length) break;

            if (swit[left] == swit[right]) {
                start = left;
                end = right;
            } else {
                break;
            }
        }

        // 대칭 구간을 배열에 저장
        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        // 리스트를 배열로 변환
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
