package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
    static int R, C;
    static char[][] map;
    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            String temp = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        int diff = map[0][0] - 'A';
        diff = 1 << diff;
        move(0, 0, 1, diff);
        System.out.println(answer);
    }
    static int answer = 1;
    public static void move(int i, int j, int count, int diff) {
        answer = Math.max(answer, count);
        for(int index = 0; index < 4; index++) {
            int nextI = i + dirI[index];
            int nextJ = j + dirJ[index];
            if(nextI < 0 || nextI >= R || nextJ < 0 || nextJ >= C) {
                continue;
            }

            int nextDiff = map[nextI][nextJ] - 'A';
            nextDiff = 1 << nextDiff;
            if((diff & nextDiff) == 0) {
                move(nextI, nextJ, count + 1, (diff | nextDiff));
            }
        }
    }
}