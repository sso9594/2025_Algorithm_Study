import java.util.*;

public class PGM여행경로 {
	public static int N;
	public static ArrayList<String>[] list;
	public static ArrayList<String[]> possible_list;
	public static String[] cities;
	public static String[][] tickets_copy;
	public static boolean[] v;
	
	public static void main(String[] args) {
		
		String[][] t = {
				{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}
		};
		
		System.out.println(Arrays.toString(solution(t)));
	}

	public static String[] solution(String[][] tickets) {

		tickets_copy = deepCopy(tickets);
		int cnt = 0;
		HashSet<String> set = new HashSet<>();

		N = tickets.length;
		for(int i =0; i<N; i++) {
			set.add(tickets[i][0]);
			set.add(tickets[i][1]);
		}

		int airports = set.size();
		cities = set.toArray(new String[0]); 
		possible_list = new ArrayList<>();
		v = new boolean[N];

		list = new ArrayList[airports];
		for(int i =0; i<airports; i++) {
			list[i] = new ArrayList<>();
		}

		for(int i =0; i< N; i++) {
			String start = tickets[i][0];
			String end = tickets[i][1];

			for(int j = 0; j<airports; j++) {				
				if(start.equals(cities[j])) {
					list[j].add(end);
				}
			}
		}

		//System.out.println(Arrays.deepToString(list));
		dfs("ICN", 0, new String[N+1]);

		Collections.sort(possible_list, (o1, o2) -> {
			for(int i =0; i<o1.length; i++) {
				if(o1[i] != o2[i]) {
					return o1[i].compareTo(o2[i]);
				}
			}
			return 0;
		});


		String[] answer = possible_list.get(0);
		return answer;
	}
	private static String[][] deepCopy(String[][] tickets) {
		// TODO Auto-generated method stub
		String[][] nstr = new String[tickets.length][2];
		for(int i =0; i<tickets.length; i++) {
			for(int j =0; j<2; j++) {
				nstr[i][j] = tickets[i][j];
			}
		}

		return nstr;
	}

	private static void dfs(String now, int depth, String[] visited) {
		// TODO Auto-generated method stub

		if(depth >= N) {
			visited[depth] = now;
			possible_list.add(visited);
			return;
		}

		visited[depth] = now;

		int now_num = -1;
		for(int i = 0; i<cities.length; i++) {
			if(cities[i].equals(now)) now_num = i;
		}

		A : for(int i = 0; i<list[now_num].size(); i++) {
			String next = list[now_num].get(i);

			int now_flight = 0;
			boolean can_flight = false;

			for(int j = 0; j<N; j++) { // tickets 뒤져서 start == 0 end == 1이면 걔 v[j] = true
				if(now.equals(tickets_copy[j][0]) && next.equals(tickets_copy[j][1])) {

					if(!v[j]) {
						can_flight = true;
						now_flight = j;
					}
				}
			}

			if(!can_flight) continue A;

			v[now_flight] = true;
			dfs(next, depth+1, visited.clone());
			v[now_flight] = false;
			// 아까 걔 false
		}

	}
}
