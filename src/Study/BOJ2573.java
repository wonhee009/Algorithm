package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2573 {
    static int row;
    static int col;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while(true) {
            int count = check();
            if(count == 0) {
                year = 0;
                break;
            }
            if(count == -1) {
                break;
            }
            melting();
            year++;
        }
        System.out.println(year);
    }

    public static void melting() {
        Queue<Index> q = new LinkedList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                 if(map[i][j] <= 0) {
                     continue;
                 }
                 int count = map[i][j];
                 for(int dir = 0; dir < 4; dir++) {
                     int nextI = i + dirI[dir];
                     int nextJ = j + dirJ[dir];

                     if(nextI < 0 || nextI >= row || nextJ < 0 || nextJ >= col) {
                         continue;
                     }
                     if(map[nextI][nextJ] <= 0) {
                         count--;
                     }
                 }
                 if(count < 0) {
                     count = 0;
                 }
                 Index temp = new Index(i, j);
                 temp.count = count;
                 q.add(temp);
            }
        }

        while(!q.isEmpty()) {
            Index temp = q.poll();
            map[temp.i][temp.j] = temp.count;
        }
    }

    // 빙산이 두 덩이 이상인지 아닌지 확인
    static int[][] visit;
    public static int check() {
        visit = new int[row][col];
        int number = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(map[i][j] != 0 && visit[i][j] == 0) {
                    number++;
                    if(number >= 2) {
                        return -1;
                    }
                    visitor(number, new Index(i, j));
                }
            }
        }
        return number;
    }
    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void visitor(int number, Index cur) {
        Stack<Index> s = new Stack<>();
        s.push(cur);
        while(!s.isEmpty()) {
            Index temp = s.pop();
            visit[temp.i][temp.j] = number;
            for(int dir = 0; dir < 4; dir++) {
                int nextI = temp.i + dirI[dir];
                int nextJ = temp.j + dirJ[dir];

                if(nextI < 0 || nextI >= row || nextJ < 0 || nextJ >= col || visit[nextI][nextJ] == number) {
                    continue;
                }
                if(map[nextI][nextJ] > 0) {
                    s.push(new Index(nextI, nextJ));
                }
            }
        }
    }
}

class Index {
    int i;
    int j;
    int count;
    Index(int i, int j) {
        this.i = i;
        this.j = j;
    }
}