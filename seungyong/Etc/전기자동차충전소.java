package seungyong.Etc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class 전기자동차충전소 {
    static int N;
    static int[][] map;
    static Home[] homes;
    static int result;
    static class Home {
        int x, y, d;
        Home(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static class Charger {
        int x, y;
        Charger(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();

            homes = new Home[N];
            result = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                int x = sc.nextInt() + 15;
                int y = sc.nextInt() + 15;
                int d = sc.nextInt();
                homes[i] = new Home(x, y, d);
            }

            for (int i = 1; i <= 2; i++) {
                permu(0, 0, i, new ArrayList<>());
                if(result!=Integer.MAX_VALUE) break;
            }
            
            System.out.println("#" + test_case + " " + (result != Integer.MAX_VALUE ? result : -1));
        }
    }

    static void permu(int count, int index, int num, ArrayList<Charger> sel){
        if(count==num){
            calcDistance(sel);
            return;
        }

        a:for (int i = index; i < 900; i++) {
            int x = i / 30;
            int y = i % 30;

            for (Home home : homes) {
                if(home.x==x && home.y==y){
                    continue a;
                }
            }

            sel.add(new Charger(x, y));
            permu(count+1, i+1, num, sel);
            sel.remove(sel.size()-1);
        }
    }

    static void calcDistance(ArrayList<Charger> sel){
        int sum = 0;
        for (int i = 0; i < homes.length; i++) {
            Home current = homes[i];
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < sel.size(); j++) {
                Charger currentCharger = sel.get(j);
                min = Math.min(Math.abs(currentCharger.x-current.x) + 
                    Math.abs(currentCharger.y-current.y), min);
            }
            if(min > current.d){
                return;
            }
            sum += min;
        }
        result = Math.min(result, sum);
    }
}