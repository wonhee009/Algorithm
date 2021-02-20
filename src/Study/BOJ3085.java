package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3085 {
    static int N;
    static char[][] map;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i = 0; i < N; i++) {
            String temp = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j + 1 < N && map[i][j + 1] != map[i][j]) {
                    swap(i, j, i, j + 1);
                    check();
                    swap(i, j, i, j + 1);
                }
                if(i + 1 < N && map[i + 1][j] != map[i][j]) {
                    swap(i, j, i + 1, j);
                    check();
                    swap(i, j, i + 1, j);
                }
            }
        }
        System.out.println(answer);
    }

    public static void swap(int startI, int startJ, int endI, int endJ) {
        char temp = map[startI][startJ];
        map[startI][startJ] = map[endI][endJ];
        map[endI][endJ] = temp;
    }

    public static void check() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                checkRow(i, j);
                checkCol(i, j);
            }
        }
    }

    public static void checkRow(int i, int j) {
        int count = 1;
        char target = map[i][j];

        int nextI = i;
        while(true) {
            nextI -= 1;
            if(nextI < 0 || map[nextI][j] != target) {
                break;
            }
            count++;
        }

        nextI = i;
        while(true) {
            nextI += 1;
            if(nextI >= N || map[nextI][j] != target) {
                break;
            }
            count++;
        }
        answer = Math.max(count, answer);
    }

    public static void checkCol(int i, int j) {
        int count = 1;
        char target = map[i][j];

        int nextJ = j;
        while(true) {
            nextJ -= 1;
            if(nextJ < 0 || map[i][nextJ] != target) {
                break;
            }
            count++;
        }

        nextJ = j;
        while (true) {
            nextJ += 1;
            if(nextJ >= N || map[i][nextJ] != target) {
                break;
            }
            count++;
        }
        answer = Math.max(count, answer);
    }
}
