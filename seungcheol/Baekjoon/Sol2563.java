import java.util.Scanner;

public class Sol2563 {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    // 정사각형 도화지
    int[][] page = new int[100][100];

    //정사각형 초기화
    for(int i = 0; i < 100; i++){
        for(int j = 0; j <100; j++){
            page[i][j] = 0;
        }
    }
    for(int i = 0; i <n; i++){
        int x = sc.nextInt();
        int y = sc.nextInt();

        for(int j = x; j < x+10; j++){
            for(int k = y; k < y+10; k++){
                page[j][k] = 1;
            }
        }
    }
    int count = 0;
    for(int i = 0; i < 100; i++){
        for(int j = 0; j < 100; j++){
            if(page[i][j] == 1){
                count+=1;
            }
        }
    }
    System.out.println(count);
    }
}
