import java.util.Scanner;

public class Q알고스탁 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();

		for(int test_case = 1; test_case <=T; test_case++) {

			int Ms = sc.nextInt();		// 예치
			int Ma = sc.nextInt();		// 납입

			int N = sc.nextInt();		// 종목 수
			int L = sc.nextInt();		// 개월

			int[][] map = new int[N][L+1];	// dp
			for(int i=0; i<N; i++) {
				for(int j =0; j<L+1; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int budget = Ms;
			int next_get = 0;	// 담달에 받을 돈

			// 총 L개월간 진행
			for(int month=0; month<L; month++) {
				//납입
				if(month > 0) budget += Ma;
				budget += next_get;
				//System.out.println(month + "에 매도받은 금액 : " + next_get);
				next_get = 0;
				boolean[] visited = new boolean[N];
				
				// 다시 한번 도는 이유 : 남는 돈도 다음 차익 순위에 투자
				for(int ps = 0; ps < N; ps++) {
					// 최대 차익 종목 선정
					int max_benefit_idx = Integer.MIN_VALUE;
					int max_benefit_val = Integer.MIN_VALUE;

					for(int n = 0; n<N; n++) {	// 모든 종목 비교
						if(map[n][month+1] - map[n][month] > max_benefit_val && !visited[n]) {	// 최대 수익보다 더 큰 수익을 낼 때
							max_benefit_idx = n;
							max_benefit_val = map[n][month+1] - map[n][month];	// 1주당 차익
						}
					}

					visited[max_benefit_idx] = true;

					if(max_benefit_val <= 0) {  // 더 안봐도 됨
						//System.out.println(month+":"+budget);
						break;
					}else {						// 최대 구매 + 수익 산정
						int cnt = 0;
						// max_benefit_idx를 butget이 가능한 만큼 구매
						while(true) {
							if(budget >= map[max_benefit_idx][month] * cnt) cnt++;
							else break;
						}
						cnt -= 1;

						System.out.println(budget+ "있어서 " + (max_benefit_idx+1) + "번 종목" + cnt + "개 매수");
						// budget -= 구매 개수 * 이번 달 가격;
						budget -= cnt * map[max_benefit_idx][month];
						next_get += cnt * map[max_benefit_idx][month+1];
						System.out.println("다음 달에 받을 돈 : " + next_get);
					}
				}
				System.out.println(month + ":" + budget);
			}
			budget += Ma;
			budget += next_get;

			int ans = budget - (Ma*L + Ms);

			sb.append("#" + test_case + " " + ans).append('\n');

		}

		System.out.print(sb.toString());

	}

}
