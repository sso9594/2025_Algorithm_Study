import java.util.Scanner;

public class Q17070 {
    public static int N, cnt;
    public static int[][] map;

public static void main(String[] args) {
    // TODO Auto-generated method stub

    Scanner sc = new Scanner(System.in);
    
    N = sc.nextInt();
    
    map = new int[N][N];
    for(int i =0; i<N; i++) {
        for(int j =0; j<N; j++) {
            map[i][j] = sc.nextInt();
        }            
    }

    pipe_move(0, 0, 0, 1, 0);
    
    System.out.println(cnt);
}

public static void pipe_move(int x1, int y1, int x2, int y2, int now_state) {
    
    
    if(x2 == N-1 && y2 == N-1) {
        cnt++;
        return;
    }
    // 종료조건
    if(now_state == 0 && y2 >= N-1) return;
    if(now_state == 2 && x2 >= N-1) return;        
    
    //System.out.println(x2 + " " + y2 + " " + now_state);

    // 분기    (나가거나 || 1이면 안감)
    if(now_state == 0){
        // next_state == 0
        if(map[x2][y2+1] != 1) pipe_move(x2, y2, x2, y2+1, 0); 
        // next_state == 1
        if(x2 < N-1) {                
            if(map[x2][y2+1] != 1 && map[x2+1][y2] != 1 && map[x2+1][y2+1] != 1) pipe_move(x2, y2, x2+1, y2+1, 1);
        }
    }
    // now_state == 1
    else if(now_state == 1) {
        // next_state == 0
        if(y2 < N-1) {
            if(map[x2][y2+1] != 1) pipe_move(x2, y2, x2, y2+1, 0); 
        }
        
        // next_state == 1
        if(x2 < N-1 && y2 < N-1) {
            if(map[x2+1][y2] != 1 && map[x2+1][y2+1] != 1 && map[x2][y2+1] != 1) pipe_move(x2, y2, x2+1,y2+1, 1);

        }
        
        // next_state == 2
        if(x2 < N-1) {
            if(map[x2+1][y2] != 1) pipe_move(x2, y2, x2+1, y2, 2);
        }
        
    }
    // now_state == 2
    else if(now_state == 2) {
        // next_state == 2
        if(map[x2+1][y2] != 1) pipe_move(x2, y2, x2+1, y2, 2);
        // next_state == 1
        if(y2 < N-1) {
            if(map[x2+1][y2] != 1 && map[x2+1][y2+1] != 1 && map[x2][y2+1] != 1) pipe_move(x2, y2, x2+1,y2+1, 1);
        }
    }
        
}
}

