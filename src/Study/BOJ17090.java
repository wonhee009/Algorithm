package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17090 {
    static int N, M;
    static int[][] map;
    static Queue<PuzzleIndex> q = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            String temp = br.readLine();
            for(int j = 0; j < M; j++) {
                char t = temp.charAt(j);
                switch (t) {
                    case 'U':
                        map[i][j] = 0;
                        break;
                    case 'R':
                        map[i][j] = 1;
                        break;
                    case 'D':
                        map[i][j] = 2;
                        break;
                    default:
                        map[i][j] = 3;
                        break;
                }
            }
        }
        addQueue();
        judge();
        System.out.println(answer);
    }

    public static void addQueue() {
        // left & right
        for(int i = 0; i < N; i++) {
            if(map[i][0] == 3) {
                q.add(new PuzzleIndex(i, 0));
            }
            if(map[i][M - 1] == 1) {
                q.add(new PuzzleIndex(i, M - 1));
            }
        }
        // top & bottom
        for(int j = 0; j < M; j++) {
            if(map[0][j] == 0) {
                q.add(new PuzzleIndex(0, j));
            }
            if(map[N - 1][j] == 2) {
                q.add(new PuzzleIndex(N - 1, j));
            }
        }
    }

    static int[] dirI = {-1, 0, 1, 0};
    static int[] dirJ = {0, 1, 0, -1};
    static int answer = 0;
    public static void judge() {
        boolean[][] visit = new boolean[N][M];
        for(PuzzleIndex p : q) {
            visit[p.i][p.j] = true;
        }
        answer = q.size();
        while(!q.isEmpty()) {
            PuzzleIndex temp = q.poll();
            for(int index = 0; index < 4; index++) {
                int judgeI = temp.i + dirI[index];
                int judgeJ = temp.j + dirJ[index];

                if(judgeI < 0 || judgeI >= N || judgeJ < 0 || judgeJ >= M || visit[judgeI][judgeJ]) {
                    continue;
                }

                int targetI = judgeI + dirI[map[judgeI][judgeJ]];
                int targetJ = judgeJ + dirJ[map[judgeI][judgeJ]];

                if(targetI == temp.i && targetJ == temp.j) {
                    q.add(new PuzzleIndex(judgeI, judgeJ));
                    visit[judgeI][judgeJ] = true;
                    answer++;
                }
            }
        }
    }
}
class PuzzleIndex {
    int i;
    int j;
    PuzzleIndex(int i, int j) {
        this.i = i;
        this.j = j;
    }
}