package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ4485 {
    static class Zelda {
        int i;
        int j;
        int cost;

        Zelda(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
    static int N;
    static int[][] map;
    static ArrayList<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) {
                break;
            }
            map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            check();
            T++;
        }
        int size = answer.size();
        for(int i = 0; i < size; i++) {
            System.out.println("Problem " + (i + 1) + ": " + answer.get(i));
        }
    }

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void check() {
        int[][] visited = new int[N][N];
        boolean[][] checked = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Zelda> q = new PriorityQueue<>(new Comparator<Zelda>() {
            @Override
            public int compare(Zelda o1, Zelda o2) {
                return o1.cost - o2.cost;
            }
        });
        q.add(new Zelda(0, 0, map[0][0]));
        visited[0][0] = map[0][0];
        checked[0][0] = true;
        while(!q.isEmpty()) {
            Zelda temp = q.poll();
            checked[temp.i][temp.j] = true;
            for(int index = 0; index < 4; index++) {
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];

                if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N || checked[nextI][nextJ]) {
                    continue;
                }
                if(visited[nextI][nextJ] >= map[nextI][nextJ] + temp.cost) {
                    visited[nextI][nextJ] = map[nextI][nextJ] + temp.cost;
                    q.add(new Zelda(nextI, nextJ, map[nextI][nextJ] + temp.cost));
                }
            }
        }

        answer.add(visited[N - 1][N - 1]);
    }
}