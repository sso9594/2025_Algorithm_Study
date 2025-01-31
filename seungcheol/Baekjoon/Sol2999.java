
import java.util.Scanner;

public class Sol2999 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.next();

        int r = 1;
        int c = line.length();

        for(int i = 1; i < line.length(); i++){
            if(i*i > line.length()) break;

            if(line.length() % i == 0){
                r = i;
                c = line.length()/i;
            }
        }

        char[][] map = new char[c][r];

        int strLoc = 0;
        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                map[i][j] = line.charAt(strLoc);
                strLoc++;
            }
        }
        
        String result = "";

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                result+= map[j][i];
            }
        }

        System.out.print(result);
    }
}