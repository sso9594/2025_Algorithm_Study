import java.util.*;
import java.io.*;

public class 치킨배달_115686 {

    static int M;
    static int N;
    static int[][] arr;
    static ArrayList<boolean[][]> chicken;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][N];
        chicken = new ArrayList<>();
        int result = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        findChicken(new boolean[N][N], 0);

        for(boolean[][] chickenArr : chicken){
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int min = Integer.MAX_VALUE;
                    if(arr[i][j]==1){
                        for(int x=0; x < N; x++){
                            for (int y = 0; y < N; y++) {
                                if(arr[x][y] == 2 && chickenArr[x][y]){
                                    int distance = Math.abs(x-i) + Math.abs(y-j);
                                    min = Math.min(distance, min);
                                }
                            }
                        }
                        sum += min;
                    }
                }
            }
            result = Math.min(sum, result);
        }

        System.out.println(result);

    }

    static int comb(int i, int j, boolean[][] visited, int count){
        int min = Integer.MAX_VALUE;
            if(arr[i][j]==1){
                for(int x=0; x < N; x++){
                    for (int y = 0; y < N; y++) {
                        if(arr[x][y] == 2 && visited[x][y]){
                            int distance = Math.abs(x-i) + Math.abs(y-j);
                            min = Math.min(distance, min);
                        }
                    }
                }
                return min  == Integer.MAX_VALUE ? 0 : min;
            }
        return 0;        
    }

    static void findChicken(boolean[][] visited, int count){

        if(count == M){
            boolean[][] copy = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(visited[i], 0, copy[i], 0, N);
            }
            chicken.add(copy);  
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 2 && !visited[i][j]){
                    visited[i][j] = true;
                    findChicken(visited, count+1);
                    visited[i][j] = false;
                }
            }
        }
    }

}
