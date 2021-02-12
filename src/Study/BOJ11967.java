package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11967 {
    static class LightIndex {
        int i;
        int j;

        LightIndex(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int N;
    static ArrayList<LightIndex>[][] room;
    static boolean[][] light;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        light = new boolean[N][N];
        light[0][0] = true;

        room = new ArrayList[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                room[i][j] = new ArrayList<>();
            }
        }
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startI = Integer.parseInt(st.nextToken()) - 1;
            int startJ = Integer.parseInt(st.nextToken()) - 1;
            int endI = Integer.parseInt(st.nextToken()) - 1;
            int endJ = Integer.parseInt(st.nextToken()) - 1;

            room[startI][startJ].add(new LightIndex(endI, endJ));
        }

        check();
        System.out.print(count());
    }
    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void check() {
        Queue<LightIndex> q = new LinkedList<>();
        q.add(new LightIndex(0, 0));
        boolean[][] visit = new boolean[N][N];
        visit[0][0] = true;
        while(!q.isEmpty()) {
            LightIndex temp = q.poll();
            Queue<LightIndex> willLight = new LinkedList<>();
            if(!room[temp.i][temp.j].isEmpty()) {
                for(LightIndex t : room[temp.i][temp.j]) {
                    if(light[t.i][t.j]) {
                        continue;
                    }
                    willLight.add(t);
//                    light[t.i][t.j] = true;
                }
            }

            for(int index = 0; index < 4; index++) {
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];

                if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N || visit[nextI][nextJ]) {
                    continue;
                }
                if(light[nextI][nextJ]) {
                    q.add(new LightIndex(nextI, nextJ));
                    visit[nextI][nextJ] = true;
                }
            }
            while(!willLight.isEmpty()) {
                LightIndex l = willLight.poll();
                light[l.i][l.j] = true;
                if(l.i + 1 < N && visit[l.i + 1][l.j]) {
                    q.add(l);
                    visit[l.i][l.j] = true;
                    continue;
                }
                if(l.i - 1 >= 0 && visit[l.i - 1][l.j]) {
                    q.add(l);
                    visit[l.i][l.j] = true;
                    continue;
                }
                if(l.j + 1 < N && visit[l.i][l.j + 1]) {
                    q.add(l);
                    visit[l.i][l.j] = true;
                    continue;
                }
                if(l.j - 1 >= 0 && visit[l.i][l.j - 1]) {
                    q.add(l);
                    visit[l.i][l.j] = true;
                    continue;
                }
            }
        }
    }
    public static int count() {
        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(light[i][j]) {
                    answer++;
                }
            }
        }
        return answer;
    }
}