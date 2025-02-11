import java.util.Scanner;

public class Sol17070 {
    static int total = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        boolean[][] v = new boolean[n][n];
        //initalize
        v[0][0] = true;
        v[0][1] = true;
        int[] startPoint = new int[]{0,1};

        recursive(map, startPoint, 0, v,0);
        System.out.println(total);

}
static void recursive(int[][] map, int[] position, int d ,boolean[][] v, int count){
    if(position[0] == map[0].length-1 && position[1] == map[0].length-1){
        total++;
        return;
    };
    
    int xdx;
    int ydy;
    //가로
    if(d == 0){
        xdx = position[0];
        ydy = position[1]+1;
        if(xdx >= 0 && xdx < map[0].length && ydy >=0 && ydy < map[0].length){
            if(map[xdx][ydy] == 0 && v[xdx][ydy] == false){
                v[xdx][ydy] = true; 
                count++;
                recursive(map, new int[]{xdx,ydy}, 0, v,count);
                v[xdx][ydy] = false; 
                
            }
        }
        xdx = position[0]+1;
        ydy = position[1]+1;
        if(xdx >= 0 && xdx < map[0].length && ydy >=0 && ydy < map[0].length){
            if(map[xdx][ydy] == 0 && map[xdx-1][ydy] == 0 && map[xdx][ydy-1] == 0&& v[xdx][ydy] == false){
                v[xdx][ydy] = true; 
                count++;
                recursive(map, new int[]{xdx,ydy}, 2, v,count);
                v[xdx][ydy] = false;
            }
        }
    }
    //세로
    if(d == 1){
        xdx = position[0]+1;
        ydy = position[1];
        if(xdx >= 0 && xdx < map[0].length && ydy >=0 && ydy < map[0].length){
            if(map[xdx][ydy] == 0&& v[xdx][ydy] == false){
                v[xdx][ydy] = true; 
                count++;
                recursive(map, new int[]{xdx,ydy}, 1, v,count);
                v[xdx][ydy] = false;
            }
        }
        xdx = position[0]+1;
        ydy = position[1]+1;
        if(xdx >= 0 && xdx < map[0].length && ydy >=0 && ydy < map[0].length){
            if(map[xdx][ydy] == 0 && map[xdx-1][ydy] == 0 && map[xdx][ydy-1] == 0&& v[xdx][ydy] == false){
                v[xdx][ydy] = true; 
                count++;
                recursive(map, new int[]{xdx,ydy}, 2, v,count);
                v[xdx][ydy] = false;
            }
        }
    }
    //대각선
    if(d == 2){
        xdx = position[0];
        ydy = position[1]+1;
        if(xdx >= 0 && xdx < map[0].length && ydy >=0 && ydy < map[0].length){
            if(map[xdx][ydy] == 0&& v[xdx][ydy] == false){
                v[xdx][ydy] = true; 
                count++;
                recursive(map, new int[]{xdx,ydy}, 0, v,count);
                v[xdx][ydy] = false;
            }
        }
        xdx = position[0]+1;
        ydy = position[1];
        if(xdx >= 0 && xdx < map[0].length && ydy >=0 && ydy < map[0].length){
            if(map[xdx][ydy] == 0&& v[xdx][ydy] == false){
                v[xdx][ydy] = true; 
                count++;
                recursive(map, new int[]{xdx,ydy}, 1, v,count);
                v[xdx][ydy] = false;
            }
        }
        xdx = position[0]+1;
        ydy = position[1]+1;
        if(xdx >= 0 && xdx < map[0].length && ydy >=0 && ydy < map[0].length){
            if(map[xdx][ydy] == 0 && map[xdx-1][ydy] == 0 && map[xdx][ydy-1] == 0&& v[xdx][ydy] == false){
                v[xdx][ydy] = true; 
                count++;
                recursive(map, new int[]{xdx,ydy}, 2, v,count);
                v[xdx][ydy] = false;
            }
        }
    }
}
}

