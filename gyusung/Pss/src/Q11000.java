import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q11000 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][2];
		int max = Integer.MIN_VALUE;
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for(int i =0; i<N; i++) {
			 st = new StringTokenizer(br.readLine());
				
			 arr[i][0] = Integer.parseInt(st.nextToken());
			 arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, ((o1, o2) -> Integer.compare(o1[0], o2[0])));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i =0; i<N; i++) {
			if(pq.isEmpty() || pq.peek() > arr[i][0]) {
				pq.add(arr[i][1]);
			}else if(pq.peek() <= arr[i][0]) {
				pq.poll();
				pq.add(arr[i][1]);
			}
		}
		
		
		System.out.println(pq.size());
		
	}
}
