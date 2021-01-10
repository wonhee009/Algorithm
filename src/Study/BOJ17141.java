package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17141 {
    static int n, m;
    static int[][] map;
    static int answer = -1;
    static int emptyCount;
    static ArrayList<VirusIndex> canVirus = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        emptyCount = n * n;

        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    canVirus.add(new VirusIndex(i, j));
                }
                if(map[i][j] == 1) {
                    emptyCount--;
                }
            }
        }
        boolean[] visit = new boolean[canVirus.size()];
        choice(visit, 0, 0);
        System.out.println(answer);
    }

    // m개의 바이러스 자리 고르기
    public static void choice(boolean[] visit, int count, int currentIndex) {
        if(count == m) {
            check(visit);
            return;
        }
        if(currentIndex >= visit.length) {
            return;
        }
        visit[currentIndex] = true;
        choice(visit, count + 1, currentIndex + 1);
        visit[currentIndex] = false;
        choice(visit, count, currentIndex + 1);
    }

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void check(boolean[] visit) {
        int target = emptyCount - m;
        boolean[][] isVirus = new boolean[n][n];
        Queue<VirusIndex> q = new LinkedList<>();
        for(int i = 0; i < visit.length; i++) {
            if (visit[i]) {
                VirusIndex temp = canVirus.get(i);
                q.add(new VirusIndex(temp.i, temp.j));
                isVirus[temp.i][temp.j] = true;
            }
        }
        int time = 0;
        while (!q.isEmpty() && target > 0) {
            int size = q.size();
            for(int s = 0; s < size; s++) {
                VirusIndex temp = q.poll();
                for(int index = 0; index < 4; index++) {
                    int nextI = temp.i + dirI[index];
                    int nextJ = temp.j + dirJ[index];

                    if(nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n || isVirus[nextI][nextJ] || map[nextI][nextJ] == 1) {
                        continue;
                    }

                    isVirus[nextI][nextJ] = true;
                    target--;
                    q.add(new VirusIndex(nextI, nextJ));
                }
            }
            time++;
        }
        if (target == 0) {
            if(answer == -1) {
                answer = time;
            } else {
                answer = Math.min(answer, time);
            }
        }
    }
}

class VirusIndex {
    int i;
    int j;

    VirusIndex(int i, int j) {
        this.i = i;
        this.j = j;
    }
}