package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {
    static int N, M;
    static int[][] map;
    static int answer = -1;
    static int emptySpace = 0;
    static ArrayList<Virus3Index> virus = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virus.add(new Virus3Index(i, j));
                }
                if(map[i][j] == 0) {
                    emptySpace++;
                }
            }
        }

        boolean[] visit = new boolean[virus.size()];
        choiceVirus(visit, 0, 0);
        System.out.println(answer);
    }

    public static void choiceVirus(boolean[] visit, int count, int currentIndex) {
        if(count == M) {
            judge(visit);
            return;
        }
        if(currentIndex >= visit.length) {
            return;
        }
        visit[currentIndex] = true;
        choiceVirus(visit, count + 1, currentIndex + 1);
        visit[currentIndex] = false;
        choiceVirus(visit, count, currentIndex + 1);
    }

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void judge(boolean[] visit) {
        boolean[][] isVirus = new boolean[N][N];
        Queue<Virus3Index> q = new LinkedList<>();
        for(int i = 0; i < visit.length; i++) {
            if(visit[i]) {
                Virus3Index temp = virus.get(i);
                q.add(new Virus3Index(temp.i, temp.j));
                isVirus[temp.i][temp.j] = true;
            }
        }
        int target = emptySpace;
        int time = 0;
        while(!q.isEmpty() && target > 0) {
            int len = q.size();
            for(int s = 0; s < len; s++) {
                Virus3Index temp = q.poll();
                for(int index = 0; index < 4; index++) {
                    int nextI = temp.i + dirI[index];
                    int nextJ = temp.j + dirJ[index];

                    if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N) {
                        continue;
                    }
                    if(map[nextI][nextJ] == 1 || isVirus[nextI][nextJ]) {
                        continue;
                    }
                    if(map[nextI][nextJ] != 2) {
                        target--;
                    }
                    isVirus[nextI][nextJ] = true;
                    q.add(new Virus3Index(nextI, nextJ));
                }
            }
            time++;
        }
        if(target == 0) {
            if(answer == -1) {
                answer = time;
            } else {
                answer = Math.min(answer, time);
            }
        }
    }
}
class Virus3Index {
    int i;
    int j;

    Virus3Index(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
