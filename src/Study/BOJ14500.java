package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {
    static int N, M, max; // 가로 세로
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
    static int[] dx = { 0, 0, -1, 1 };

    // 범위 검사
    public static boolean isValid(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M)
            return false;
        return true;
    }

    public static void tetromino() {
        // map 전체 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                exception(i, j);
            }
        }
    }

    // ㅗ 모양 제외 모양들 구현
    private static int dfs(int row, int col, int depth, int sum) {

        if (depth == 4) {
            max = Math.max(max, sum);
            return 0;
        }

        for (int k = 0; k < 4; k++) {
            int nextRow = row + dy[k];
            int nextCol = col + dx[k];

            if (isValid(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, depth + 1, sum + map[nextRow][nextCol]);
                visited[nextRow][nextCol] = false;

            }
        }

        return sum;
    }

    // ㅗ 모양 구현
    private static void exception(int row, int col) {
        int wing = 4; // 가운데에서의 상하좌우 날개
        int min = Integer.MAX_VALUE;
        int sum = map[row][col];
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            // 날개가 2개이상 없다면 ㅗ 모양이 아니다. 그러므로 함수를 종료한다.
            if (wing <= 2)
                return;
            // 날개가 맵 바깥에 있는 경우
            if (!isValid(nextRow, nextCol)) {
                wing--;
                continue;
            }
            min = Math.min(min, map[nextRow][nextCol]);
            sum = sum + map[nextRow][nextCol];
        }
        // 날개가 4개일때 가장 작은 날개를 없애야 ㅗ,ㅏ,ㅓ,ㅜ 모양이 나온다.
        if (wing == 4) {
            sum = sum - min;
        }
        max = Math.max(max, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        map = new int[N][M]; // 입력받을 맵
        visited = new boolean[N][M]; // 방문 체크
        max = 0; // 최댓값

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of input

        tetromino();

        System.out.println(max);
        br.close();
    }
}
