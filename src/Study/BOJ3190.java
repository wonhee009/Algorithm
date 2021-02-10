package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3190 {
    static class Snake {
        int i;
        int j;
        Snake(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int N;
    static boolean[][] map, snakeMap;
    static HashMap<Integer, String> direction = new HashMap<>();
    static int[] dirI = {0, 1, 0, -1};
    static int[] dirJ = {1, 0, -1, 0};

    static List<Snake> snakeArray = new ArrayList<>();
    static int dir = 0;
    static int second = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        snakeMap = new boolean[N][N];
        int K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = true;
        }
        int L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            direction.put(sec, dir);
        }

        snakeArray.add(0, new Snake(0, 0));
        snakeMap[0][0] = true;

        while(moveSnake()) {
            second++;
        }
        System.out.println(second);
    }
    public static boolean moveSnake() {
        Snake temp = snakeArray.get(0);
        int nextI = temp.i + dirI[dir];
        int nextJ = temp.j + dirJ[dir];
        if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N || snakeMap[nextI][nextJ]) {
            return false;
        }
        snakeMap[nextI][nextJ] = true;
        snakeArray.add(0, new Snake(nextI, nextJ));
        // 사과
        if(map[nextI][nextJ]) {
            map[nextI][nextJ] = false;

        } else {
            Snake tail = snakeArray.remove(snakeArray.size() - 1);
            snakeMap[tail.i][tail.j] = false;
        }

        if(direction.containsKey(second)) {
            String turnDir = direction.get(second);
            if (turnDir.equals("D")) {
                dir++;
                if(dir == 4) {
                    dir = 0;
                }
            } else {
                dir--;
                if(dir < 0) {
                    dir = 3;
                }
            }
        }
        return true;
    }
}
