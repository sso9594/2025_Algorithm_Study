import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        for (int t = 1; t <= testCase; t++){
            // 데이터 입력
            int n = sc.nextInt();
            int[] trees = new int[n];
            for (int i = 0; i < n; i++){
                trees[i] = sc.nextInt();
            }
            
            // maxTree값 구하기
            int maxTree = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++){
                if(trees[i] > maxTree){
                    maxTree = trees[i];
                } 
            }
            
            // 각 나무에 대해, maxTree와의 차이(Δ)를 3일 주기로 분해
            // (2일에 3씩 성장하는 패턴)
            int ones = 0;   // Δ % 3 == 1 인 경우 (홀수 날만 필요)
            int twos = 0;   // Δ % 3 == 2 인 경우 (짝수 날로 처리)
            int cycleCount = 0; // 각 나무별 Δ/3의 합 (각 cycle은 2일 소요)
            for (int i = 0; i < n; i++){
                int delta = maxTree - trees[i];
                if(delta != 0){
                    cycleCount += delta / 3;
                    int r = delta % 3;
                    if(r == 1){
                        ones++;
                    } else if(r == 2){
                        twos++;
                    }
                }
            }
            
            // 우선, cycle에 해당하는 일수 (각 cycle은 2일)
            int treeDays = cycleCount * 2;
            
            int extra;
            if(ones >= twos) {
                extra = ones + (twos*2);
            } else {
                extra = 2 * twos;
            }
            
            treeDays += extra;
            
            System.out.println("#" + t + " " + treeDays);
        }
    }
}
