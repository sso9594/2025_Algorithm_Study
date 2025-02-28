import java.util.Arrays;
import java.util.Scanner;

class SSol1861{
    static int value = 0;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        for(int t = 1; t <= testCase; t++){
            int n = sc.nextInt();
            int[][] map = new int[n][n];

            for(int i = 0; i < n; i++){
                for(int j = 0; j< n; j++){
                    map[i][j] = sc.nextInt();
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int[] sp = new int[]{i,j};
                    boolean[][] v = new boolean[n][n];
                    v[sp[0]][sp[1]] = true;

                    dfs(map, 1, sp, Arrays.copyOf(sp, sp.length), n, v);

                }
            }
            
            System.out.println("#"+t+" "+value + " " + max);
            value = 0; 
            max = Integer.MIN_VALUE;
        }
    }

    static void dfs(int[][] map, int cnt, int[] sp, int[] mp, int n, boolean[][] v){
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        for(int i = 0; i < 4; i++){
            int xdx = mp[0] + dx[i];
            int ydy = mp[1] + dy[i];
            if(xdx >= 0 && xdx < n && ydy >= 0 && ydy < n && !v[xdx][ydy]){
                if(Math.abs(map[xdx][ydy] - map[mp[0]][mp[1]]) == 1){
                    mp[0] = xdx;
                    mp[1] = ydy;
                    v[xdx][ydy] = true;
                    cnt++;

                    if(max <= cnt){
                        if(max == cnt){
                            if(value > map[sp[0]][sp[1]]){
                                value = map[sp[0]][sp[1]];
                                max = cnt;
                            }
                        }else{
                            value = map[sp[0]][sp[1]];
                            max = cnt;
                        }

                    }
                    
                    dfs(map, cnt, sp, mp, n, v);
                }
            }
        }
    }

}