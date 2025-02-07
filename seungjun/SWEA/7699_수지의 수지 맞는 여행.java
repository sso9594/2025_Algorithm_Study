import java.util.Scanner;

class Solution {
    static int R, C, answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] map;
    static boolean[] visited;

    static void search_alphabet(int i, int j, int alphabet_cnt) {
        if (answer < alphabet_cnt) {
            answer = alphabet_cnt;
        }

        for (int d=0; d<4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (nr>=0 && nr<R && nc>=0 && nc<C) {
                int alphabet = map[nr][nc] - 'A';
                if (!visited[alphabet]) {
                    visited[alphabet] = true;
                    search_alphabet(nr, nc, alphabet_cnt+1);
                    visited[alphabet] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();

        for (int tc=1; tc<=T; tc++) {
            R = input.nextInt();
            C = input.nextInt();
            map = new char[R][C];
            visited = new boolean[26];
            answer = 0;

            for (int i=0; i<R; i++) {
                String temp = input.next();
                for (int j=0; j<C; j++) {
                    map[i][j] = temp.charAt(j);
                }
            }
            visited[map[0][0]-'A'] = true;

            search_alphabet(0, 0, 1);

            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}
