package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ8980 {
    static class Truck {
        int from;
        int to;
        int count;

        Truck(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }
    }
    static int N, C, M;
    static PriorityQueue<Truck> q = new PriorityQueue<>(new Comparator<Truck>() {
        @Override
        public int compare(Truck o1, Truck o2) {
            if(o2.to == o1.to) {
                return o2.from - o1.from;
            }
            return o1.to - o2.to;
        }
    });
    static int currentTruck = 0;
    static int[] vill;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        vill = new int[N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            q.add(new Truck(from, to, count));
        }
        int answer = 0;
        while(!q.isEmpty()) {
            Truck temp = q.poll();
            int can = temp.count;
            boolean flag = false;
            for(int index = temp.from; index < temp.to; index++) {
                if(vill[index] >= C) {
                    flag = true;
                    break;
                }
                can = Math.min(can, C - vill[index]);
            }
            if(flag) {
                continue;
            }
            for(int index = temp.from; index < temp.to; index++) {
                vill[index] += can;
            }
            answer += can;
        }
        System.out.println(answer);
    }
}
