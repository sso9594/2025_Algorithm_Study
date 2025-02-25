import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //data 입력
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        
        //country 초기화
        int[][] countries = new int[n][n];
        for(int i = 0; i < n;i++){
            for(int j = 0; j < n; j++){
                countries[i][j] = sc.nextInt();
            }
        }

        //setting
        ArrayDeque<Integer[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        
        int[] dx = new int[]{0,1,0,-1};
        int[] dy = new int[]{1,0,-1,0};

        int day = 0;

        while(true){
            boolean check = false;
            //하루 동안 국경이 열려있는지 확인
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    //방문하지 않은 국가 접근
                    if(visited[i][j] == false){
                        queue.push(new Integer[]{i,j});
                        visited[i][j] = true;
                    
    
                    //연결되어 있는 국경 확인
                    List<Integer> conn = new ArrayList<>(); // 연결된 국가의 인구수
                    List<Integer[]> unions = new ArrayList<>(); // 연합 국가 리스트트
    
                    //연합 국가 찾기기
                    while(!queue.isEmpty()){
                        Integer[] pos = queue.poll();
                        conn.add(countries[pos[0]][pos[1]]);
                        unions.add(pos);
                        for(int k = 0; k < 4; k++){
                            int xdx = pos[0] + dx[k];
                            int ydy = pos[1] + dy[k];
    
                            if(xdx >= 0 && xdx < n && ydy >= 0 && ydy < n && visited[xdx][ydy] == false){
                                double diff = Math.abs(countries[xdx][ydy] - countries[pos[0]][pos[1]]);
                                if(diff >= l && diff <= r){
                                    visited[xdx][ydy] = true;
                                    queue.offer(new Integer[]{xdx,ydy});
                                    //한번이라도 queue에 담기면 인구 이동이 있다고 판단
                                    check = true;
                                }
                            }   
                        }
                    }
                    //연합 국가들 평균값 찾기
                    int sum = 0;
                    for(int k = 0; k < conn.size(); k++){
                        sum += conn.get(k);
                    }
                    int avg = sum / conn.size();
    
                    //연합 평균값으로 교체
                    for(Integer[] union: unions){
                        countries[union[0]][union[1]] = avg;
                    }
                }
                }
            }
            //한번이라도 인구이동하면 day 증가가
            if(check){
                day++;
                visited = new boolean[n][n];
            }else{
                break;
            }
        }
        System.out.println(day);
    }

}