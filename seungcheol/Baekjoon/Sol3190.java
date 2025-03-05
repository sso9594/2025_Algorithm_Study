
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;



class Sol3190{
    static class Snake{
        int x;
        int y;
        int nextTurn;

        public Snake(int x, int y, int nextTurn) {
            this.x = x;
            this.y = y;
            this.nextTurn = nextTurn;
        }

        
    }
    static class SnakeTurnInfo{
        int x;
        char d;

        public SnakeTurnInfo(int x, char d) {
            this.x = x;
            this.d = d;
        }
        
    }
    public static void main(String[] args) {
        //map 입력
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] map = new int[n][n];
        
        //사과(2) 위치 입력
        for(int i = 0; i < k ; i++){
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            map[x][y] = 2;
        }

        //뱀 방향 변환 정보
        Queue<SnakeTurnInfo> queue = new ArrayDeque<>();
        int l = sc.nextInt();
        for(int i = 0; i < l; i++){
            int x = sc.nextInt();
            char d = sc.next().charAt(0);
            queue.offer(new SnakeTurnInfo(x,d));
        }

        //setting
        List<Snake> snake = new ArrayList<>();
        int d = 1; //0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽
        snake.add(new Snake(0,0,d));

        //snake(1) 배치
        map[snake.get(0).x][snake.get(0).y] = 1;

        int sec = 0;
        while(true){
            //뱀의 방향 전환 정보
            if(!queue.isEmpty() && queue.peek().x == sec){
                SnakeTurnInfo info = queue.poll();
                if(info.d == 'L'){
                    d--;
                    if(d == -1) d = 3;
                }else{
                    d = (d+1)%4;
                }
            }
            //먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치 시킨다.
            Snake snakeHead = snake.get(0);
            int nextX = snakeHead.x;
            int nextY = snakeHead.y;
            
            if(d == 0){        // 위쪽
                nextX -= 1;
            } else if(d == 1){ // 오른쪽
                nextY += 1;
            } else if(d == 2){ // 아래쪽
                nextX += 1;
            } else if(d == 3){ // 왼쪽
                nextY -= 1;
            }

            //초 증가
            sec++;

            //벽에 부딪히면 끝
            if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n){
                if(map[nextX][nextY] == 1) break; //자기 자신과 몸이 부딪히면 끝
            }else break;

            //사과가 있다면
            if(map[nextX][nextY] == 2){
                snake.add(0,new Snake(nextX, nextY, d));
                map[nextX][nextY] = 1;

            }else{
                snake.add(0,new Snake(nextX, nextY, d));
                map[nextX][nextY] = 1;
                //마지막 꼬리 삭제
                int idxTail = snake.size()-1;
                map[snake.get(idxTail).x][snake.get(idxTail).y] = 0;
                snake.remove(idxTail);
            }
            

            
        }
        System.out.println(sec);
        
        

    }
    
}