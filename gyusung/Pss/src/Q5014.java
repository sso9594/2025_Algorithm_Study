import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Q5014 {

public static int F, G;
public static int[] visited;
public static class Node{
    int stair, depth;
    
    public Node(int stair, int depth) {
        this.stair = stair;
        this.depth = depth;
    }
}

public static void main(String[] args) {
    // TODO Auto-generated method stub

    Scanner sc = new Scanner(System.in);
    
    F = sc.nextInt();
    int S = sc.nextInt();
    G = sc.nextInt();
    int U = sc.nextInt();
    int D = sc.nextInt();
    
    visited = new int[F + 1];

    ArrayDeque<Node> queue = new ArrayDeque<>();
    
    queue.add(new Node(S, 1));
    visited[S] = 1;
    
    while(!queue.isEmpty()) {
        Node now = queue.poll();
        //System.out.println(now.stair);
        
        if(now.stair + U <= F && visited[now.stair + U] == 0) {
            visited[now.stair + U] = now.depth + 1;
            queue.add(new Node(now.stair + U, now.depth + 1));
        }
        
        if(now.stair - D > 0 && visited[now.stair - D] == 0) {
            visited[now.stair - D] = now.depth + 1;
            queue.add(new Node(now.stair - D, now.depth + 1));
        }
            
        //System.out.println(Arrays.toString(visited));
    }
    
    if(visited[G] == 0) System.out.println("use the stairs");
    else System.out.println(visited[G] - 1);
}
}