import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sol17281 {
    static List<Integer[]> batterOrder = new ArrayList<>();

    public static void main(String[] args) {
        //기본 데이터 입력
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //이닝 데이터 입력
        int[][] innings = new int[n][9];

        for(int i = 0; i < n; i++){
            int[] inning = new int[9];
            for(int j = 0; j < 9; j++){
                inning[j] = sc.nextInt();
            }
            innings[i] = inning;
        }

        //타자 순서 정하기
        int[] batters = new int[8];
        for(int i = 1; i<=8;i++){
            batters[i-1] = i;
        }

        permOrder(batters, new int[8] , 0, new boolean[8]);

        int maxScore = 0;

        //타자의 순서들을 가져온다
        for(Integer[] order : batterOrder){
            //game을 초기화 한다.
            boolean[] base = new boolean[3]; //0 base1, 1 base2, 2 base3
            int outCount = 0;
            int currentBattar = 0;
            int totalScore = 0;

            for(int[] inning : innings){//각 이닝 별 타자의 점수

                while(true){
                    //타자가 야구공을 침
                    int score = inning[order[currentBattar]];
                    currentBattar = (currentBattar+1) % 9;

                    if(score == 0)outCount++;

                    if(outCount == 3){
                        //새 이닝 시작
                        // currentBattar = 0;
                        Arrays.fill(base, false);
                        outCount = 0;
                        break;
                    }; //아웃 카운트가 3일 때

                    //각각 상황에 따라 결정
                    if(score == 1){// 안타
                        if(base[2]){
                            base[2]= false;
                            totalScore++;
                        }if(base[1]){
                            base[1] = false;
                            base[2] = true;
                        }if(base[0]){
                            base[0] = false;
                            base[1] = true;
                        }
                        base[0] = true;
                    }else if(score == 2){//2루타
                        if(base[2]){
                            base[2]= false;
                            totalScore++;
                        }if(base[1]){
                            base[1] = false;
                            totalScore++;
                        }if(base[0]){
                            base[0] = false;
                            base[2] = true;
                        }
                        base[1] = true;
                    }else if(score == 3){//3루타
                        if(base[2]){
                            base[2]= false;
                            totalScore++;
                        }if(base[1]){
                            base[1] = false;
                            totalScore++;
                        }if(base[0]){
                            base[0] = false;
                            totalScore++;
                        }
                        base[2] = true;
                    }else if(score == 4){//홈런
                        if(base[2]){
                            base[2]= false;
                            totalScore++;
                        }if(base[1]){
                            base[1] = false;
                            totalScore++;
                        }if(base[0]){
                            base[0] = false;
                            totalScore++;
                        }
                        totalScore++;
                    }
                }
            }
            if(totalScore > maxScore){
                maxScore = totalScore;
            }
        }
        System.out.println(maxScore);
    }

    static void permOrder(int[] batters, int[] sel, int depth, boolean[] v){
        if(depth == 8){
            Integer[] temp = new Integer[9];
            for(int i = 0; i < 9; i++){
                if(i == 3){
                    temp[i] = 0;
                }else if(i > 3){
                    temp[i] = sel[i-1];
                }else{
                    temp[i] = sel[i];
                }
            }
            batterOrder.add(temp);
            return;
        }
    
        for(int i = 0; i < 8; i++){
            if(!v[i]){
                v[i] = true;
                sel[depth] = batters[i];
                permOrder(batters, sel, depth + 1, v);
                v[i] = false;
            }
        }
    }
}
