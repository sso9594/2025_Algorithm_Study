
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sol1235 {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    
    List<String> lines = new ArrayList<>(); 

    for(int i = 0; i < n; i++){
        lines.add(sc.next());
    }

    int result = lines.get(0).length();
    int strLength = lines.get(0).length();

    for(int i = 0 ; i < strLength; i++){
        //0번 인덱스 삭제
        List<String> removedLines = new ArrayList<>();
        for(int j = 0 ; j < lines.size(); j++){
            removedLines.add(lines.get(j).substring(1));
        }
        lines = removedLines;
        boolean check = true;

        for(int j = 0; j < lines.size(); j++){
            String targetString = lines.get(j);
            for (int k = j+1; k < lines.size(); k++){
                if(targetString.equals(lines.get(k))){
                    check = false;
                    break;
                }
            }
            if(check == false) break;
        }

        if(check == true){
            result = lines.get(0).length();
        }

    }
    System.out.println(result);



    }
}
