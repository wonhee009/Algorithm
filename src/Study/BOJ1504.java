package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1504 {
    static class Index {
        int des;
        int cost;

        Index(int des, int cost) {
            this.des = des;
            this.cost = cost;
        }
    }
    static int N, E;
    static ArrayList<Index>[] road;
    static int firstTarget, secondTarget;
    static int[] startZero, startFirst, startSecond;
    static int inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        road = new ArrayList[N];
        for(int i = 0; i < N; i++){
            road[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            road[a].add(new Index(b, c));
            road[b].add(new Index(a, c));
        }
        st = new StringTokenizer(br.readLine());
        firstTarget = Integer.parseInt(st.nextToken()) - 1;
        secondTarget = Integer.parseInt(st.nextToken()) - 1;

        startZero = new int[N];
        startFirst = new int[N];
        startSecond = new int[N];

        Arrays.fill(startZero, inf);
        Arrays.fill(startFirst, inf);
        Arrays.fill(startSecond, inf);

        fillZero();
        fillFirst();
        fillSecond();

        int answer = -1;
        if(startZero[firstTarget] != inf && startFirst[secondTarget] != inf && startSecond[N - 1] != inf) {
            answer = startZero[firstTarget] + startFirst[secondTarget] + startSecond[N - 1];
        }
        if(startZero[secondTarget] != inf && startSecond[firstTarget] != inf && startFirst[N - 1] != inf) {
            answer = Math.min(answer, startZero[secondTarget] + startSecond[firstTarget] + startFirst[N - 1]);
        }

        System.out.println(answer);
    }

    public static void fillZero() {
        PriorityQueue<Index> q = new PriorityQueue<>(new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                return o1.cost - o2.cost;
            }
        });
        boolean[] checked = new boolean[N];
        q.add(new Index(0, 0));
        checked[0] = true;
        startZero[0] = 0;

        while(!q.isEmpty()){
            Index temp = q.poll();
            checked[temp.des] = true;

            for(Index r : road[temp.des]) {
                if(temp.cost + r.cost < startZero[r.des]) {
                    startZero[r.des] = temp.cost + r.cost;
                    q.add(new Index(r.des, temp.cost + r.cost));
                }
            }
        }
    }

    public static void fillFirst() {
        PriorityQueue<Index> q = new PriorityQueue<>(new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                return o1.cost - o2.cost;
            }
        });
        boolean[] checked = new boolean[N];
        q.add(new Index(firstTarget, 0));
        checked[firstTarget] = true;
        startFirst[firstTarget] = 0;

        while(!q.isEmpty()){
            Index temp = q.poll();
            checked[temp.des] = true;

            for(Index r : road[temp.des]) {
                if(temp.cost + r.cost < startFirst[r.des]) {
                    startFirst[r.des] = temp.cost + r.cost;
                    q.add(new Index(r.des, temp.cost + r.cost));
                }
            }
        }
    }

    public static void fillSecond() {
        PriorityQueue<Index> q = new PriorityQueue<>(new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                return o1.cost - o2.cost;
            }
        });
        boolean[] checked = new boolean[N];
        q.add(new Index(secondTarget, 0));
        checked[secondTarget] = true;
        startSecond[secondTarget] = 0;

        while(!q.isEmpty()){
            Index temp = q.poll();
            checked[temp.des] = true;

            for(Index r : road[temp.des]) {
                if(temp.cost + r.cost < startSecond[r.des]) {
                    startSecond[r.des] = temp.cost + r.cost;
                    q.add(new Index(r.des, temp.cost + r.cost));
                }
            }
        }
    }
}