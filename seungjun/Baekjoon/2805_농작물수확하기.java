import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        int[][] bat;

        for (int tc=1; tc<=T; tc++) {
            int N = input.nextInt();
            int sum = 0;
            int expr;
            bat = new int[N][N];

            for (int i=0; i<N; i++) {
                String[] temp = input.next().split("");
                for (int j=0; j<N; j++) {
                    bat[i][j] = Integer.parseInt(temp[j]);
                }
            }
            for (int i=0; i<N; i++) {
                if (i<=N/2) expr = i;
                else expr = N/2-i+N/2;
                for (int j = 0; j < 2 * expr + 1; j++) {
                    sum += bat[i][(j - expr) + N / 2];
                }
            }
            System.out.printf("#%d %d\n",tc, sum);
        }

    }
}
