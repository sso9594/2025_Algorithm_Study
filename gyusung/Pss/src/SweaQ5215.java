import java.util.Arrays;
import java.util.Scanner;

public class SweaQ5215 {

	static int L, N;
	static int max;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		
		int[] score = new int[N];
		int[] calories = new int[N];
		
		for(int i=0; i<N; i++){
			score[i] = sc.nextInt();
			calories[i] = sc.nextInt();
		}
		
		recurse(score, calories, 0, 0, 0);
		System.out.println(max);
	}

	private static void recurse(int[] score, int[] calories, int idx, int total_score, int now_cal) {
		// TODO Auto-generated method stub

		System.out.println(total_score + " " + now_cal);
		
		if(now_cal > L) return;
		// 값 비교
		if(total_score> max) {
			max = total_score;
		}

		if(idx >= N) return;
		
		// 선택안한거
		recurse(score, calories, idx+1, total_score, now_cal);
		// 선택한거
		recurse(score, calories, idx+1, total_score+score[idx], now_cal+calories[idx]);			
	}

}
