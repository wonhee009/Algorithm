package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1941 {
    static int[][] map = new int[5][5];
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 다솜: 1, 도연: -1
        for(int i = 0; i < 5; i++) {
            String temp = br.readLine();
            for(int j = 0; j < 5; j++) {
                char t = temp.charAt(j);
                if(t == 'S') {
                    map[i][j] = 1;
                }
                else {
                    map[i][j] = -1;
                }
            }
        }

        boolean[] visit = new boolean[25];
        choice(0, visit, 0, 0);

        System.out.println(answer);
    }

    public static void choice(int count, boolean[] visit, int index, int sum) {
        if(count == 7) {
            if (sum > 0) {
                boolean[][] team = new boolean[5][5];
                for(int i = 0; i < 25; i++) {
                    team[i / 5][i % 5] = visit[i];
                }
                int[][] check = new int[5][5];
                int number = 1;
                boolean flag = true;
                for(int i = 0; i < 5; i++) {
                    for(int j = 0; j < 5; j++) {
                        if(check[i][j] == 0 && team[i][j]) {
                            if(number > 1) {
                                flag = false;
                                continue;
                            }
                            checkTeam(check, number, i, j, team);
                            number++;
                        }
                    }
                    if(flag == false) {
                        continue;
                    }
                }
                if (flag) {
                    answer++;
                }
            }
            return;
        }
        if(index > 24) {
            return;
        }
        visit[index] = true;
        choice(count + 1, visit, index + 1, sum + map[index / 5][index % 5]);
        visit[index] = false;
        choice(count, visit, index + 1, sum);
    }
    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void checkTeam(int[][] check, int number, int i, int j, boolean[][] team){
        check[i][j] = number;
        for(int index = 0; index < 4; index++ ) {
            int nextI = i + dirI[index];
            int nextJ = j + dirJ[index];

            if(nextI < 0 || nextI >= 5 || nextJ < 0 || nextJ >= 5) {
                continue;
            }
            if(team[nextI][nextJ] && check[nextI][nextJ] == 0) {
                checkTeam(check, number, nextI, nextJ, team);
            }
        }
    }
}
