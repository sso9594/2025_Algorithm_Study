import java.io.*;
import java.util.*;

public class Solution14510v2 {
    static int T;
    static int[] trees;

    public static int getDay(int N) {
        int highest = trees[N - 1]; // 가장 큰 나무 키

        int day = 0;
        int one = 0; // 나머지가 1인 경우 개수 (d % 3 == 1)
        int two = 0; // 나머지가 2인 경우 개수 (d % 3 == 2)

        for (int i = 0; i < N; i++) {
            int d = highest - trees[i]; // 설레는 키 차이
            day += (d / 3) * 2; // 키 3씩 자라면 2일 걸림

            if(d % 3 == 1) {
                one++;
            } else if(d % 3 == 2) {
                two++;
            }
        }

        int pair = Math.min(one, two);
        day += pair * 2; // 키 3씩 자라면 2일 걸림
        one -= pair;
        two -= pair;

        if (one > 0) {
            day += 2 * one - 1;
        } else if (two > 0) {
            day += two + 1 + ((two - 1) / 3);
        }
        return day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            trees = new int[N];
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
            }

            int day = getDay(N);

            System.out.printf("#%d %d\n", t, day);
        }
    }
}