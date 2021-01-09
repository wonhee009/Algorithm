package Test;

import java.util.*;

public class TidiOne {
    public static void main(String[] args) throws Exception {
        int[] testCar = {22, 9, 1, 15, 8, 6, 20, 7, 11, 5, 10, 4, 1};
        int[][] testLink = {
                {4, 7},
                {13,10},
                {6, 3},
                {7, 1},
                {6, 12},
                {5, 11},
                {5, 6},
                {5, 10},
                {9, 8},
                {8, 11},
                {8, 2},
                {7, 8}
        };
        System.out.println(solution(13, testCar, testLink));

        int[] testCar2 = {6, 4, 10, 9, 8, 4};
        int[][] testLink2 = {
                {4, 1},
                {3, 2},
                {1, 6},
                {3, 5},
                {5, 1}
        };
        System.out.println(solution(6, testCar2, testLink2));
    }

    static ArrayList<Integer>[] child;
    static Queue<Integer> leafs = new LinkedList<>();
    static boolean[][] map;
    static int[] sum;
    public static int solution(int n, int[] cars, int[][] links) {
        child = new ArrayList[n + 1];
        sum = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            child[i] = new ArrayList<Integer>();
        }
        map = new boolean[n + 1][n + 1];
        checkLeaf(links, n);
        checkMap(links);
        checkChild(n);
        int max = 0;
        for(int i = 1; i <= n; i++) {
            sumPlace(i, cars);
            max = Math.max(max, sum[i]);
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(map[i][j]) {
                    int diff = Math.abs((max - sum[j]) - sum[j]);
                    answer = Math.min(diff,answer);
                }
            }
        }

        return answer;
    }
    public static void checkLeaf(int[][] links, int n) {
        int[] linkCount = new int[n + 1];
        int len = links.length;
        for(int l = 0; l < len; l++) {
            linkCount[links[l][0]]++;
            linkCount[links[l][1]]++;
        }
        for(int i = 1; i <= n; i++) {
            if (linkCount[i] == 1) {
                leafs.add(i);
            }
        }
    }
    public static void checkMap(int[][] links) {
        int len = links.length;
        for(int i = 0; i < len; i++) {
            int start = links[i][0];
            int end = links[i][1];
            map[start][end] = true;
            map[end][start] = true;
        }
    }
    public static void checkChild(int n) {
        boolean[][] visit = new boolean[n + 1][n + 1];
        while(!leafs.isEmpty()) {
            int leaf = leafs.poll();
            for(int i = 1; i <= n; i++) {
                if(!visit[leaf][i] && map[leaf][i]) {
                    visit[leaf][i] = true;
                    visit[i][leaf] = true;
                    child[i].add(leaf);
                    leafs.add(i);
                }
            }
        }
    }
    public static void sumPlace(int root, int[] cars) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        int count = 0;
        while(!q.isEmpty()) {
            int r = q.poll();
            count += cars[r - 1];
            ArrayList<Integer> list = child[r];
            if(list.isEmpty()) {
                continue;
            }
            for(int size = 0; size < list.size(); size++) {
                q.add(list.get(size));
            }
        }
        sum[root] = count;
    }
}
