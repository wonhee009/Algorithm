package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14442 {
    static int n, m, k;
    static int[][] map;
    static int[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new int[n][m];

        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }
        int answer = judge();
        System.out.println(answer);
    }
    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static int judge() {
        PriorityQueue<WallIndex> q = new PriorityQueue<>(new Comparator<WallIndex>() {
            @Override
            public int compare(WallIndex o1, WallIndex o2) {
                return o1.count - o2.count;
            }
        });
        q.add(new WallIndex(0, 0, 1, 0));
        visit[0][0] = 0;
        while(!q.isEmpty()) {
            WallIndex temp = q.poll();
            if(temp.i == n - 1 && temp.j == m - 1) {
                return temp.count;
            }
            for(int index = 0; index < 4; index++) {
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];

                if(nextI < 0|| nextI >= n || nextJ < 0 || nextJ >= m) {
                    continue;
                }
                if((visit[nextI][nextJ] & (1 << temp.k)) > 0) {
                    continue;
                }
                int v = visit[nextI][nextJ] | (1 << temp.k);
                //  벽
                if(map[nextI][nextJ] == 1) {
                    if(temp.k >= k) {
                        continue;
                    }
                    visit[nextI][nextJ] = v;
                    q.add(new WallIndex(nextI, nextJ, temp.count + 1, temp.k + 1));
                    continue;
                }
                visit[nextI][nextJ] = v;
                q.add(new WallIndex(nextI, nextJ, temp.count + 1, temp.k));
            }

        }
        return -1;
    }

    static class WallIndex {
        int i;
        int j;
        int count;
        int k;

        WallIndex(int i, int j, int count, int k) {
            this.i = i;
            this.j = j;
            this.count = count;
            this.k = k;
        }
    }
}
