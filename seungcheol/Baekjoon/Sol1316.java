import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Sol1316 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int result = 0;

        for (int i = 0; i < n; i++){
            String line = sc.next();
            Set<Character> set = new HashSet<>();
            for(char word: line.toCharArray()){
                set.add(word);
            }
            //각 단어의 위치를 저장
            int[] wordLoc = new int[line.length()];
            Arrays.fill(wordLoc, 0); 
            
            boolean totalCheck = true;

            for(char s : set){
                Arrays.fill(wordLoc, 0); 
                for(int j = 0; j < line.length(); j++){
                    if(line.charAt(j) == s){
                        wordLoc[j] = 1;
                    }
                }
                boolean check = true;
                int startIdx = -1;
                //처음 1찾기
                for (int j = 0; j < wordLoc.length; j++){
                    if (wordLoc[j] == 1){
                        startIdx = j;
                        break;
                    }
                }

                boolean checkZero = false;
                for(int j = startIdx; j < wordLoc.length; j++){
                    if(wordLoc[j] == 0){
                        checkZero = true;
                    }
                    if(checkZero && wordLoc[j] == 1){
                        check = false;
                        break;
                    }
                }
                if(check == false){
                    totalCheck = false;
                    break;
                }
            }
            if(totalCheck){
                result++;
            }
        }
        System.out.println(result);
    }
}
