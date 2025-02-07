
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sol1260 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int v = sc.nextInt();

        int[][] graph = new int[n][n];
        for(int i= 0; i < m; i++){
            int tm = sc.nextInt();
            int tn = sc.nextInt();
            graph[tm-1][tn-1] = 1;
            graph[tn-1][tm-1] = 1;
        }

        dfs(graph, v-1, new boolean[n]);
        System.out.println();
        bfs(graph, v-1, new boolean[n]);

    }


    static void dfs (int[][] graph, int v, boolean[] visit){
        System.out.print(v+1+" ");
        visit[v] = true;
        int[] startArr = graph[v];
        for(int i = 0; i <startArr.length;i++){
            if(startArr[i] == 1 && visit[i] == false){
                dfs(graph, i, visit);
            }
        }
    }

    static void bfs(int[][] graph, int v, boolean[] visit){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visit[v] = true;

        while(!queue.isEmpty()){
            int cv = queue.poll();
            int[] startArr = graph[cv];
            System.out.print(cv+1+" ");
            
            for(int i = 0; i < startArr.length; i++){
                if(!visit[i] && startArr[i] == 1){
                    visit[i] = true;
                    queue.add(i);
                }
                
            }


        }



    }
}   