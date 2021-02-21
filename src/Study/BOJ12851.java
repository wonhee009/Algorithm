package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int limit = 100000;
        boolean[] visit = new boolean[limit + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        visit[N] = true;

        if(N == K) {
            System.out.println(0);
            System.out.println(0);
            return;
        }

        int second = 0;
        int count = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int s = 0; s < size; s++) {
                int temp = q.poll();
                visit[temp] = true;
                if(temp - 1 == K) {
                    count++;
                } else if(temp - 1 >= 0 && !visit[temp - 1]) {
                    q.add(temp - 1);
                }
                if(temp + 1 == K) {
                    count++;
                } else if(temp + 1 <= limit && !visit[temp + 1]) {
                    q.add(temp + 1);
                }
                if(temp * 2 == K) {
                    count++;
                } else if(temp * 2 <= limit && !visit[temp * 2]) {
                    q.add(temp * 2);
                }
            }
            second++;
            if (count != 0) {
                break;
            }
        }
        System.out.println(second);
        System.out.println(count);
    }
}
