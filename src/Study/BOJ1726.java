package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726 {
    static int row, col;
    static int[][] map;
    static boolean[][][] visit;
    static RobotIndex robot;
    static RobotIndex target;
    static Queue<RobotIndex> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        visit = new boolean[row][col][4];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        robot = new RobotIndex(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        st = new StringTokenizer(br.readLine());
        target = new RobotIndex(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        q.add(robot);
        visit[robot.i][robot.j][robot.dir] = true;
        int answer = 0;
        while (!q.isEmpty()) {
            RobotIndex temp = q.poll();
            // 동일 위치 -> 방향만 확인
            if (temp.i == target.i && temp.j == target.j && temp.dir == target.dir) {
                answer = temp.count;
                break;
            }
            move(temp);
        }
        System.out.println(answer);
    }

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    static int[] right = {2, 3, 1, 0};
    static int[] left = {3, 2, 0, 1};

    public static void move(RobotIndex r) {
        // 방향 회전
        if (!visit[r.i][r.j][right[r.dir]]) {
            RobotIndex rightRobot = new RobotIndex(r.i, r.j, right[r.dir]);
            rightRobot.count = r.count + 1;
            visit[r.i][r.j][right[r.dir]] = true;
            q.add(rightRobot);
        }
        if (!visit[r.i][r.j][left[r.dir]]) {
            RobotIndex leftRobot = new RobotIndex(r.i, r.j, left[r.dir]);
            leftRobot.count = r.count + 1;
            visit[r.i][r.j][left[r.dir]] = true;
            q.add(leftRobot);
        }

        for(int n = 1; n <= 3; n++) {
            int nextI = r.i + dirI[r.dir] * n;
            int nextJ = r.j + dirJ[r.dir] * n;

            if (!(nextI >= 0 && nextI < row && nextJ >= 0 && nextJ < col)) {
                break;
            }
            if (map[nextI][nextJ] == 1) {
                break;
            }

            if(visit[nextI][nextJ][r.dir]) {
                continue;
            }

            RobotIndex nextR = new RobotIndex(nextI, nextJ, r.dir);
            nextR.count = r.count + 1;
            visit[nextI][nextJ][r.dir] = true;
            q.add(nextR);
        }
    }
}

class RobotIndex {
    int i;
    int j;
    int dir;
    int count;

    RobotIndex(int i, int j, int dir) {
        this.i = i;
        this.j = j;
        this.dir = dir;
        this.count = 0;
    }
}