import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Sol7569 {
    static int y;
    static int x;
    static int h;
    static int[][][] map;
    static boolean[][][] v; 
    static int day;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        y = sc.nextInt();
        x = sc.nextInt();
        h = sc.nextInt();
        day = 0;
        
        map = new int[h][x][y];
        v = new boolean[h][x][y];

        

        //map 입력 데이터 넣기
        for(int i = 0; i < h; i++){
            for(int j = 0; j < x;j++){
                for (int k = 0; k < y; k++){
                    map[i][j][k] = sc.nextInt();
                }
            }
        }

        Queue<Integer[]> queue = new ArrayDeque<>();
        //초기 익은 토마토 queue에 넣기
        for(int i = 0; i < h; i++){
            for(int j = 0; j < x; j++){
                for(int k = 0; k < y; k++){
                    if(map[i][j][k] == 1 && v[i][j][k] == false){
                        v[i][j][k] = true;
                        queue.offer(new Integer[]{i,j,k});
                    }
                }
            }
        }

        // debugPrint();

        Queue<Integer[]> waitQueue;

        while(!queue.isEmpty()){
            //처음 완성되었다면 탈출
            if(checkMap())break;

            //queue를 기준으로 6방 탐색
            int[] dx = {0,0,-1,1,0,0};
            int[] dy = {-1,1,0,0,0,0};
            int[] dh = {0,0,0,0,1,-1};
            waitQueue = new ArrayDeque<>();
            while(!queue.isEmpty()){
                Integer[] pos = queue.poll();
                //위, 아래, 왼쪽, 오른쪽, 앞, 뒤
                int th = pos[0];
                int tx = pos[1];
                int ty = pos[2];
                for(int i = 0; i < 6; i++){
                    int xdx = tx + dx[i];
                    int ydy = ty + dy[i];
                    int hdh = th + dh[i];
                    if(xdx >=0 && xdx < x && ydy >= 0 && ydy < y && hdh >=0 && hdh < h && !v[hdh][xdx][ydy]){
                        //만약 해당 토마토 주변에 익지 않은 토마토가 있다면 익히고 익힌 토마토를 저장
                        if(map[hdh][xdx][ydy] == 0){
                            map[hdh][xdx][ydy] = 1;
                            waitQueue.offer(new Integer[]{hdh,xdx,ydy});
                            v[hdh][xdx][ydy] = true;
                        }
                    }
                }
            }
            //날짜 변경
            day++;
            queue = waitQueue;
            // System.out.println(Arrays.deepToString(queue.toArray()));
            
        }
        
        
        if(checkMap())System.out.println(day);
        else System.out.println(-1);
    }

    // static void debugPrint(){
    //     //print
    //     for(int i = 0; i < h; i++){
    //         for(int j = 0; j < x; j++){
    //             System.out.println(Arrays.toString(map[i][j]));
    //         }
    //         System.out.println();
    //     }
    // }

    static boolean checkMap(){
        for(int i = 0; i < h; i++){
            for(int j = 0; j < x; j++){
                for(int k = 0; k < y; k++){
                    if(map[i][j][k] == 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
