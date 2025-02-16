
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Sol17135 {

    static Set<Integer[]> combList = new HashSet<>();
    static int[][] map;

    public static void main(String[] args) {
        //초기 데이터 입력
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        int resultCount = 0;

        //map 함수 입력
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        //궁수의 위치 조합을 구함
        Integer[] sel = new Integer[m];
        Arrays.fill(sel, 0);
        combination(0, 0, sel, m);

        //모든 궁수 위치 조합을 반복함
        for (Integer[] archerOrder : combList) {
            //해당 라운드 맵 복사
            int[][] cMap = new int[n][m];
            for(int i = 0; i < map.length;i++){
                for(int j = 0; j < map[i].length;j++){
                    cMap[i][j] = map[i][j];
                }
            }


            //현재 적 처치 저장 변수
            int cEnemyCount = 0;

            //3명의 궁수의 공격 범위 및 순서를 저장함
            List<List<Integer[]>> archersAttackOrders = new ArrayList<>();
            for (int i = 0; i < archerOrder.length; i++) {
                if (archerOrder[i] == 1) {
                    archersAttackOrders.add(findAttackOrder(n, i, d, n, m));
                }
            }

            while (true) {
                //모든 적이 제외되면 게임 오버
                if (checkGameOver(cMap)) {
                    break;
                }

                //1 round에 공격한 적 저장
                Set<Integer[]> cAttacked = new HashSet<>();

                //3명의 궁수가 적을 공격
                for(List<Integer[]> archersAttackOrder: archersAttackOrders){
                    for(Integer[] attackOrder:archersAttackOrder){
                        //만약 해당 순서에 해당 위치가 적이면 공격한 적에 저장
                        if(cMap[attackOrder[0]][attackOrder[1]] == 1){
                            cAttacked.add(attackOrder);
                            break;
                        }
                    }
                }

                //공격 당한 적
                for(Integer[] attackedPos : cAttacked){
                    if(cMap[attackedPos[0]][attackedPos[1]] == 1){
                        cMap[attackedPos[0]][attackedPos[1]] = 0;
                        cEnemyCount += 1;
                    }

                }

                //적이 한칸씩 다가옴
                moveEnemy(cMap,n,m);

            }

            if(cEnemyCount > resultCount){
                resultCount = cEnemyCount;
            }

        }
        System.out.println(resultCount);

    }

    static boolean checkGameOver(int[][] cmap) {
        for (int i = 0; i < cmap.length; i++) {
            for (int j = 0; j < cmap[i].length; j++) {
                if (cmap[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void moveEnemy(int[][] cmap, int n, int m){
        for(int i = n-1; i>=0; i--){
            for(int j = m-1;j >=0; j--){
                if(i == n-1 && cmap[i][j] == 1){
                    cmap[i][j] = 0;
                }else if(cmap[i][j] == 1){
                    cmap[i][j] = 0;
                    cmap[i+1][j] = 1;
                }
            }
        }
    }

    static List<Integer[]> findAttackOrder(int x, int y, int d, int n, int m) {
        //궁수 공격 범위 및 순서 정의
        List<Integer[]> attackOrder = new ArrayList<>();

        //BFS 초기 값 설정 및 방문배열 초기화화
        Queue<Integer[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int order = 1;

        queue.offer(new Integer[]{x - 1, y});
        //공격 순서에 넣기
        attackOrder.add(new Integer[]{x - 1, y});
        visited[x - 1][y] = true;

        while (!queue.isEmpty()) {
            //다음 레이어 순서를 저장하는 queue
            Queue<Integer[]> temp = new ArrayDeque<>();
            //레이어 순서 변경
            order += 1;
            //공격할 수 있는 범위까지만 순서를 정함
            if (order > d) {
                break;
            }

            //한 레이어를 다 돌림
            while (!queue.isEmpty()) {
                Integer[] cPos = queue.poll();

                //3방향 넣기
                for (int i = 0; i < 3; i++) {
                    int dx = cPos[0];
                    int dy = cPos[1];

                    if (i == 0) {//왼쪽
                        dy--;
                    } else if (i == 1) {//위쪽
                        dx--;
                    } else {//오른쪽
                        dy++;
                    }

                    if (dx >= 0 && dx < n && dy >= 0 && dy < m && !visited[dx][dy]) {
                        temp.offer(new Integer[]{dx, dy});
                        //공격 순서에 넣기
                        attackOrder.add(new Integer[]{dx, dy});
                        //넣고 방문 처리
                        visited[dx][dy] = true;
                    }
                }
            }

            //다 넣었으면 queue에 다음 레이어 추가
            queue = new ArrayDeque<>(temp);
            temp.clear();
        }

        return attackOrder;
    }

    static void combination(int idx, int count, Integer[] sel, int m) {
        //3개의 궁수 위치를 모두 선택했다면, 현재 조합을 저장
        if (count == 3) {
            combList.add(Arrays.copyOf(sel, m));
            return;
        }
        //더 이상 선택할 위치가 없다면 종료
        if (idx == m) {
            return;
        }
        
        // 선택된
        sel[idx] = 1;
        combination(idx + 1, count + 1, sel, m);
        
        // 선택 안된
        sel[idx] = 0;
        combination(idx + 1, count, sel, m);
    }
}
