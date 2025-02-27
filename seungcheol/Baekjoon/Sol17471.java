import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Sol17471{
    static class City{
        int idx;
        City(int idx){
            this.idx = idx;
        }
        int people = 0;
        int[] linked = null;

        @Override
        public String toString() {
            return Integer.toString(idx);
        }
    }

    static List<City[]> powerSetList = new ArrayList<>();
    public static void main(String[] args) {
        // 데이터 입력
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //도시 인구 수 입력
        City[] cities  = new City[n];
        for(int i = 0; i < n ; i++){
            cities[i] = new City(i);
            cities[i].people = sc.nextInt();
        }

        //각 도시별 인접한 도시 입력
        for(int i = 0; i < n; i++){
            int k = sc.nextInt();
            int[] adjCity = new int[k];
            for(int j = 0; j < k; j++){
                adjCity[j] = sc.nextInt() - 1;
            }
            cities[i].linked = adjCity;
        }

        //모든 경우의 수 구하기(0이거나 다 선택 되는 경우 제외)
        for(int i = 1; i <= n/2; i++){
            powerSet(cities, 0, new City[i], new boolean[n]);
        }

        //초기 셋팅 값
        int minDiff = Integer.MAX_VALUE;
        boolean check = false;

        for(City[] set : powerSetList){
            City[] arr1 = set;
            City[] arr2 = getDiffSet(arr1, cities);
            if(checkConnected(arr1)&&checkConnected(arr2)){
                int diff = Math.abs(sumCityPeople(arr2) - sumCityPeople(arr1));
                if(diff < minDiff){
                    check = true;
                    minDiff = diff;
                }
            }
            
        }
        if(check){
            System.out.println(minDiff);
        }else{
            System.out.println(-1);
        }

        
    }

    static void powerSet(City[] arr, int depth, City[] sel, boolean[] v){
        if(depth == sel.length){
            powerSetList.add(Arrays.copyOf(sel, sel.length));
            return;
        }

        for(int i = 0; i < arr.length; i++){
            if(!v[i]){
                v[i] = true;
                sel[depth] = arr[i];
                powerSet(arr, depth+1, sel, v);
                v[i] = false;
            }
        }
    }
    static int sumCityPeople(City[] arr){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i].people;
        }

        return sum;
    }

    static City[] getDiffSet(City[] arr, City[] totalCities){
        City[] result = new City[totalCities.length - arr.length];
        int count = 0;
        for(int i = 0; i < totalCities.length; i++){
            boolean check = false;
            for(int j = 0; j < arr.length;j++){
                if(totalCities[i] == arr[j]){
                    check = true;
                    break;
                }
            }
            if(check == false){
                result[count] = totalCities[i];
                count++;
            }
        }
        return result;
    }

    static boolean checkConnected(City[] arr){
        Queue<City> queue = new ArrayDeque<>();
        boolean[] v = new boolean[arr.length];
        
        queue.offer(arr[0]);
        v[0] = true;
        while(!queue.isEmpty()){
            City target = queue.poll();
            for(int idx : target.linked){
                for(int i = 0; i < arr.length; i++){
                    if(arr[i].idx == idx && !v[i]){
                        queue.offer(arr[i]);
                        v[i] = true;
                    }
                }
            }
        }

        for(int i =0; i < v.length;i++){
            if(v[i] == false){
                return false;
            }
        }
        return true;
    }
    
}
