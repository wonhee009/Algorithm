package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2638 {
    static int N, M;
    static int[][] map;
    static Queue<CheseIndex> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    q.add(new CheseIndex(i, j));
                }
            }
        }

        int year = 0;
        while(!q.isEmpty()) {
            airCategory();
            melting();
            year++;
        }
        System.out.println(year);
    }

    static int[][] airMap;
    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void airCategory() {
        airMap = new int[N][M];
        int number = 2;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 && airMap[i][j] == 0) {
                    airChoose(number, new CheseIndex(i, j));
                    number++;
                }
            }
        }
    }
    public static void airChoose(int number, CheseIndex cur) {
        Stack<CheseIndex> s = new Stack<>();
        s.push(cur);
        while(!s.isEmpty()) {
            CheseIndex temp = s.pop();
            airMap[temp.i][temp.j] = number;
            for(int index = 0; index < 4; index++) {
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];

                if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
                    continue;
                }
                if(map[nextI][nextJ] == 0 && airMap[nextI][nextJ] == 0) {
                    s.add(new CheseIndex(nextI, nextJ));
                }
            }
        }
    }

    public static void melting() {
        int len = q.size();
        for(int i = 0; i < len; i++) {
            CheseIndex temp = q.poll();
            int count = 0;
            for(int index = 0; index < 4; index++) {
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];
                if(airMap[nextI][nextJ] == 2) {
                    count++;
                }
            }
            if (count < 2) {
                q.add(temp);
            }
            else {
                map[temp.i][temp.j] = 0;
            }
        }
    }
}
class CheseIndex {
    int i;
    int j;

    CheseIndex(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
