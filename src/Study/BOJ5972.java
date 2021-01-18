package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ5972 {
    static class RoadIndex {
        int node;
        int cost;

        RoadIndex(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static int N, M;
    static ArrayList<RoadIndex>[] road;
    static int[] distance;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        road = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            road[i] = new ArrayList<RoadIndex>();
        }
        distance = new int[N];
        checked = new boolean[N];
        for(int i = 0; i < N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for(int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());
            road[A].add(new RoadIndex(B, C));
            road[B].add(new RoadIndex(A, C));
        }

        distance[0] = 0;
        checked[0] = true;
        PriorityQueue<RoadIndex> q = new PriorityQueue<>(new Comparator<RoadIndex>() {
            @Override
            public int compare(RoadIndex o1, RoadIndex o2) {
                return o1.cost - o2.cost;
            }
        });
        q.add(new RoadIndex(0, 0));
        while(!q.isEmpty()) {
            RoadIndex temp = q.poll();
            if(temp.node == N - 1) {
                continue;
            }
            checked[temp.node] = true;
            for (RoadIndex r : road[temp.node]) {
                if(!checked[r.node] && distance[temp.node] + r.cost < distance[r.node]) {
                    distance[r.node] = distance[temp.node] + r.cost;
                    q.add(new RoadIndex(r.node, distance[r.node]));
                }
            }
        }
        System.out.println(distance[N - 1]);
    }
}

