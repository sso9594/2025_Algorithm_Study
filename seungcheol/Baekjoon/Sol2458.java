import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

class Sol2458 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] students = new int[n][n];

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            students[x - 1][y - 1] = 1;
        }

        int[] getEdge = new int[n];
        int[] toEdge = new int[n];

        // 정방향 자신보다 큰 사람이 몇명인지 구함
        for (int i = 0; i < n; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] v = new boolean[n];
            v[i] = true;
            queue.offer(i);
            while (!queue.isEmpty()) {
                int elem = queue.poll();
                for (int idx = 0; idx < students.length; idx++) {
                    if (students[elem][idx] == 1 && !v[idx]) {
                        queue.offer(idx);
                        toEdge[i]++;
                        v[idx] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] v = new boolean[n];
            v[i] = true;
            queue.offer(i);

            while (!queue.isEmpty()) {
                int elem = queue.poll();
                for (int idx = 0; idx < students.length; idx++) {
                    if (students[idx][elem] == 1 && !v[idx]) {
                        queue.offer(idx);
                        getEdge[i]++;
                        v[idx] = true;
                    }
                }
            }
        }

        // for (int i = 0; i < students.length; i++) {
        // System.out.println(Arrays.toString(students[i]));
        // }
        // System.out.println();
        // System.out.println(Arrays.toString(toEdge));
        // System.out.println(Arrays.toString(getEdge));

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (toEdge[i] + getEdge[i] == n - 1) {
                count++;
            }
        }

        System.out.println(count);
    }

}