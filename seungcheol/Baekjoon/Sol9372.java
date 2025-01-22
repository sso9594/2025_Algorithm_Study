import java.util.Scanner;

public class Sol9372 {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for(int t = 1; t <= testCase; t++){
            int c, f;
            c = sc.nextInt();
            f = sc.nextInt();

            int[][] am = new int[c][c];

            for(int fn = 0; fn < f; fn++){
                int n = sc.nextInt()-1;
                int m = sc.nextInt()-1;
                am[n][m] = 1;
                am[m][n] = 1;
            }

            boolean[] visited = new boolean[c];
            int count = dfs(am, 0, visited, 0);

            System.out.println(count);
            
        }
        
    }

    static int dfs(int[][] map, int cp, boolean[] visited, int count) {
        visited[cp] = true; 

        for (int next = 0; next < map[cp].length; next++) {
            if (map[cp][next] == 1 && !visited[next]) {
                count++; 
                count = dfs(map, next, visited, count);
            }
        }

        return count; 
    }
}
