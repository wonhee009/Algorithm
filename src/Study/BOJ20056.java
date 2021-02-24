package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20056 {
    static class FireBall {
        int i;
        int j;
        int m;
        int d;
        int s;

        FireBall(int i, int j, int m, int d, int s) {
            this.i = i;
            this.j = j;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
    static int N, M, K;
    static PriorityQueue<FireBall> q = new PriorityQueue<>(new Comparator<FireBall>() {
        @Override
        public int compare(FireBall o1, FireBall o2) {
            if (o1.i == o2.i) {
                return o1.j - o2.j;
            }
            return o1.i - o2.i;
        }
    });
    static ArrayList<FireBall>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            q.add(new FireBall(r, c, m, d, s));
        }

        for(int k = 1; k <= K; k++) {
            move();
        }
        check();
    }

    static int[] dirI = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dirJ = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void move() {
        PriorityQueue<FireBall> tempQ = new PriorityQueue<>(new Comparator<FireBall>() {
            @Override
            public int compare(FireBall o1, FireBall o2) {
                if (o1.i == o2.i) {
                    return o1.j - o2.j;
                }
                return o1.i - o2.i;
            }
        });
        while(!q.isEmpty()) {
            FireBall temp = q.poll();

            int nextI = temp.i + (dirI[temp.d] * (temp.s % N));
            int nextJ = temp.j + (dirJ[temp.d] * (temp.s % N));

            if(nextI < 0) {
                nextI += N;
            } else if(nextI >= N) {
                nextI -= N;
            }

            if(nextJ < 0) {
                nextJ += N;
            } else if(nextJ >= N) {
                nextJ -= N;
            }

            tempQ.add(new FireBall(nextI, nextJ, temp.m, temp.d, temp.s));
        }

        checkSame(tempQ);
    }

    public static void checkSame(PriorityQueue<FireBall> tempQ) {
        while (!tempQ.isEmpty()) {
            FireBall first = tempQ.poll();
            if(tempQ.isEmpty()) {
                q.add(first);
                continue;
            }
            if(!(tempQ.peek().i == first.i && tempQ.peek().j == first.j)) {
                q.add(first);
                continue;
            }
            int sumM = first.m;
            int sumS = first.s;
            boolean even = false;
            boolean odd = false;
            if(first.d % 2 == 0) {
                even = true;
            } else {
                odd = true;
            }
            int sumCount = 1;
            while(!tempQ.isEmpty() && (tempQ.peek().i == first.i && tempQ.peek().j == first.j)) {
                FireBall t = tempQ.poll();
                sumM += t.m;
                sumS += t.s;
                sumCount++;
                if(t.d % 2 == 0) {
                    even = true;
                } else {
                    odd = true;
                }
            }

            int nextM = sumM / 5;
            int nextS = sumS / sumCount;
            if(nextM == 0) {
                continue;
            }
            if(!(even && odd)) {
                q.add(new FireBall(first.i, first.j, nextM, 0, nextS));
                q.add(new FireBall(first.i, first.j, nextM, 2, nextS));
                q.add(new FireBall(first.i, first.j, nextM, 4, nextS));
                q.add(new FireBall(first.i, first.j, nextM, 6, nextS));
            } else {
                q.add(new FireBall(first.i, first.j, nextM, 1, nextS));
                q.add(new FireBall(first.i, first.j, nextM, 3, nextS));
                q.add(new FireBall(first.i, first.j, nextM, 5, nextS));
                q.add(new FireBall(first.i, first.j, nextM, 7, nextS));
            }
        }
    }

    public static void check() {
        long answer = 0;
        while(!q.isEmpty()) {
            answer += q.poll().m;
        }
        System.out.println(answer);
    }
}
