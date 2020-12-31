package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6087 {
    static int W, H;
    static char[][] map;
    static boolean[][][] visit;
    static char wall = '*';
    static char lazer = 'C';

    static LazerIndex target = null;
    static Queue<LazerIndex> q = new LinkedList<>();

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};

    static int[] firstMirror = {3, 2, 1, 0};
    static int[] secondMirror = {2, 3, 0, 1};

    static int answer = 0;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visit = new boolean[H][W][4];
        for(int i = 0; i < H; i++) {
            String temp = br.readLine();
            for(int j = 0; j < W; j++) {
                char t = temp.charAt(j);
                if(t == lazer) {
                    if(q.isEmpty()) {
                        q.add(new LazerIndex(i, j, 0,0));
                        q.add(new LazerIndex(i, j, 1,0));
                        q.add(new LazerIndex(i, j, 2,0));
                        q.add(new LazerIndex(i, j, 3,0));
                        visit[i][j][0] = true;
                        visit[i][j][1] = true;
                        visit[i][j][2] = true;
                        visit[i][j][3] = true;
                    }
                    else {
                        target = new LazerIndex(i, j, 0, 0);
                    }
                    continue;
                }
                map[i][j] = t;
            }
        }

        while(!q.isEmpty() && !flag) {
            move(q.poll());
        }
        System.out.println(answer);
    }

    public static void move(LazerIndex lazer) {
        int nextI = lazer.i + dirI[lazer.dir];
        int nextJ = lazer.j + dirJ[lazer.dir];
        while(true) {
            if(nextI < 0 || nextI >= H || nextJ < 0 || nextJ >= W || map[nextI][nextJ] == wall || visit[nextI][nextJ][lazer.dir]) {
                return;
            }
            if(nextI == target.i && nextJ == target.j) {
                flag = true;
                answer = lazer.count;
                return;
            }
            visit[nextI][nextJ][lazer.dir] = true;
            q.add(new LazerIndex(nextI, nextJ, firstMirror[lazer.dir], lazer.count + 1));
            q.add(new LazerIndex(nextI, nextJ, secondMirror[lazer.dir], lazer.count + 1));

            nextI += dirI[lazer.dir];
            nextJ += dirJ[lazer.dir];
        }
    }
}
class LazerIndex {
     int i;
     int j;
     int dir;
     int count;

     LazerIndex(int i, int j, int dir, int count) {
         this.i = i;
         this.j = j;
         this.count = count;
         this.dir = dir;
     }
}
