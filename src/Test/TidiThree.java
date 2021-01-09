package Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TidiThree {
    public static void main(String[] args) throws Exception {
        int[][] paths = {
                {1, 2, 3, 2},
                {1, 3, 2, 2},
                {2, 4, 1, 1},
                {2, 5, 4, 1},
                {3, 5, 2, 3},
                {4, 5, 2, 1}
        };
        System.out.println(solution(5, 4, paths));
    }

    static PriorityQueue<AirIndex> q = new PriorityQueue<>(new Comparator<AirIndex>() {
        @Override
        public int compare(AirIndex o1, AirIndex o2) {
            return o1.usedTime - o2.usedTime;
        }
    });
    static UsedIndex[][] map;
    static int answer = -1;
    public static int solution(int n, int m, int[][] paths) {
        map = new UsedIndex[n + 1][n + 1];
        int len = paths.length;
        for(int i = 0; i < len; i++) {
            Arrays.fill(map[i], null);
        }
        for(int i = 0; i < len; i++) {
            int start = paths[i][0];
            int end = paths[i][1];

            UsedIndex use = new UsedIndex(paths[i][3], paths[i][2]);
            map[start][end] = use;
            map[end][start] = use;
        }
        q.add(new AirIndex(1, 1, 0, 0));
        while(!q.isEmpty()) {

            AirIndex temp = q.poll();
            if(answer != -1 && temp.usedTime > answer) {
                continue;
            }
            if(temp.currentPort == n) {
                if(answer == -1) {
                    answer = temp.usedTime;
                }
                else {
                    answer = Math.min(answer ,temp.usedTime);
                }
            }
            for(int i = 1; i <= n; i++) {
                // 길 있음
                if(map[temp.currentPort][i] != null) {
                    // 방문했음
                    if((temp.visitPath & (1 << i)) != 0) {
                        continue;
                    }
                    // 예산 초과
                    if(temp.usedMoney + map[temp.currentPort][i].money > m) {
                        continue;
                    }
                    UsedIndex next = map[temp.currentPort][i];
                    q.add(new AirIndex(i, temp.visitPath | (1 << i), temp.usedTime + next.time, temp.usedMoney + next.money));
                }
            }
        }
        return answer;
    }
}
class AirIndex {
    int currentPort;
    int visitPath;
    int usedTime;
    int usedMoney;

    AirIndex(int currentPort, int visitPath, int usedTime, int usedMoney) {
        this.currentPort = currentPort;
        this.visitPath = visitPath;
        this.usedTime = usedTime;
        this.usedMoney = usedMoney;
    }
}
class UsedIndex {
    int money;
    int time;
    UsedIndex(int money, int time) {
        this.money = money;
        this.time = time;
    }
}
