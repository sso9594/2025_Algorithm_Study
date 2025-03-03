
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

class Sol2606{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int[][] edge = new int[n][n];
        for(int i = 0; i < e; i++){
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            edge[x][y] = 1;
            edge[y][x] = 1;
        }

        //debuging
        for(int i = 0; i < n; i++){
            System.out.println(Arrays.toString(edge[i]));
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;

        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int i = 0; i < n; i++){
                if(edge[node][i] == 1 && visited[i] == false){
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < visited.length;i++){
            if(visited[i]){
                count++;
            }
        }

        System.out.println(count-1);


        
    }

}