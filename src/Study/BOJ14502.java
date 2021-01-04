package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
    static int N, M;
    static Queue<BIndex> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) {
                    q.add(new BIndex(i, j));
                }
            }
        }

        wall(map, 0);
        System.out.println(answer);
    }
    static int answer = 0;
    public static void wall(int[][] map, int count) {
        if(count == 3) {
            check(map);
            return;
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    wall(map, count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }
    static int[] dirI = {0,0,1,-1};
    static int[] dirJ = {1,-1,0,0};
    public static void check(int[][] map) {
        int size = q.size();
        boolean[][] visit = new boolean[N][M];
        for(int s = 0; s < size; s++) {
            BIndex temp = q.poll();
            visit = addition(map, temp, visit);
            q.add(temp);
        }

        answer = Math.max(answer, count(visit, map));
    }
    public static boolean[][] addition(int[][] map, BIndex birus, boolean[][] visit) {
        Queue<BIndex> bQ = new LinkedList<>();
        bQ.add(birus);
        visit[birus.i][birus.j] = true;
        while(!bQ.isEmpty()) {
            BIndex temp = bQ.poll();
            for(int index = 0; index < 4; index++) {
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];

                if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M || map[nextI][nextJ] != 0 || visit[nextI][nextJ]) {
                    continue;
                }
                visit[nextI][nextJ] = true;
                bQ.add(new BIndex(nextI, nextJ));
            }
        }

        return visit;
    }
    public static int count(boolean[][] visit, int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}

class BIndex {
    int i;
    int j;
    BIndex(int i, int j) {
        this.i = i;
        this.j = j;
    }
}