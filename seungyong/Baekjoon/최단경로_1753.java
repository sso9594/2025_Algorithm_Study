import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로_1753 {
    static class Vertex implements Comparable<Vertex>{
        int e, w;
        Vertex(int e, int w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Vertex o){
            return Integer.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        int K = Integer.parseInt(br.readLine())-1;

        // 인접리스트
        ArrayList<Vertex>[] adjList = new ArrayList[V];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            adjList[s].add(new Vertex(e, w));
        }

        // Dijkstra
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[V];
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 출발 정점 넣기, 이 때 자기 자신의 가중치 이므로 0
        queue.add(new Vertex(K, 0));
        // 자기 자신의 거리는 0
        dist[K] = 0;

        while (!queue.isEmpty()) {
            // 기준 정점 큐에서 뽑기
            Vertex p = queue.poll();
            // 우선순위 큐에서 뽑았으므로 무조건 최소값의 가중치를 가진 간선이 나옴
            int minV = p.e;
            // 그 간선이 연결된 정점을 이미 방문했으면 탐색하지 않음
            if(visited[minV]) continue;
            // 아니라면 해당 정점을 방문 상태로 바꾸고 탐색 진행
            visited[minV] = true;
            // 해당 정점과 연결된 정점들 탐색
            for (Vertex target : adjList[minV]) {
                // 탐색한 정점이 방문되지 않았고, '해당 정점까지 온거리 + 탐색할 정점까지의 거리 < 탐색할 정점의 현재 거리배열의 값' 이라면
                if(!visited[target.e] && dist[minV]+target.w < dist[target.e]){
                    // 탐색할 정점의 거리배열 값을 최신화 (방문)
                    dist[target.e] = dist[minV]+target.w;
                    // 탐색할 정점을 방문
                    queue.add(new Vertex(target.e, dist[target.e]));
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.println(dist[i]==Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }
}
