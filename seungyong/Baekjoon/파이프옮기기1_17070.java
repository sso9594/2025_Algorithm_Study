import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class 파이프옮기기1_17070 {
    static int N;
    static int[][] arr;
    static int count;
    // 오른쪽 우하대각 아래
    static int[] dx = {1,1,0};
    static int[] dy = {0,1,1};
    static int[] position = {0,1,2};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();        
            }
        }

        dfs(1, 0, 0);

        System.out.println(count);

    }

    static void dfs(int x, int y, int pos){
        if(x == N-1 && y == N-1){
            count++;
            return;
        }    

        if(pos==0){ // 가로 일 경우 (가로 대각만 가능)
            // 가로
            if(isValidWidth(x, y)){
                dfs(x+dx[0], y+dy[0], position[0]);
            }
            // 대각
            if(isValidDiagonal(x, y)){
                dfs(x+dx[1], y+dy[1], position[1]);
            }
        } else if(pos==1){ // 대각 일 경우 (가로 세로 대각 가능)
            // 가로
            if(isValidWidth(x, y)){
                dfs(x+dx[0], y+dy[0], position[0]);
            }
            // 대각
            if(isValidDiagonal(x, y)){
                dfs(x+dx[1], y+dy[1], position[1]);
            }
            // 세로
            if(isValidLength(x, y)){
                dfs(x+dx[2], y+dy[2], position[2]);
            }
        } else if(pos==2){ //세로 일 경우 (대각 세로만 가능능)
            // 대각
            if(isValidDiagonal(x, y)){
                dfs(x+dx[1], y+dy[1], position[1]);
            }
            // 세로
            if(isValidLength(x, y)){
                dfs(x+dx[2], y+dy[2], position[2]);
            }
        }
    }

    static boolean isValidWidth(int x, int y){
        return x+dx[0] < N && y+dy[0] < N && arr[y+dy[0]][x+dx[0]]!=1;
    }

    static boolean isValidDiagonal(int x, int y){
        return x+dx[1] < N && y+dy[1] < N && arr[y+dy[0]][x+dx[0]]!=1
        && arr[y+dy[1]][x+dx[1]]!=1 && arr[y+dy[2]][x+dx[2]]!=1;
    }

    static boolean isValidLength(int x, int y){
        return x+dx[2] < N && y+dy[2] < N && arr[y+dy[2]][x+dx[2]]!=1;
    }
}

