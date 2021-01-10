package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TidiTwo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] test = {
                {1, 2},
                {2, 3},
                {1, 3}
        };
        System.out.println(solution(4, test));

        int[][] test2 = {
                {1, 2},
                {2, 3}
        };
        System.out.println(solution(5, test2));

        int[][] test3 = {
                {1, 2},
                {3, 4}
        };
        System.out.println(solution(6, test3));
    }

    static int[] parent;
    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b)
            parent[b] = a;
    }

    public static int solution(int n, int[][] nationality) {
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int len = nationality.length;
        for(int l = 0; l < len; l++) {
            int first = nationality[l][0];
            int second = nationality[l][1];

            union(first, second);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            int key = find(i);
            if(map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            }
            else {
                map.put(key, 1);
            }
        }

        if(map.size() < 2) {
            return 0;
        }

        Set<Integer> keys = map.keySet();
        List<Integer> count = new ArrayList<>();
        for(int key : keys) {
            count.add(map.get(key));
        }
        int answer = 0;
        for(int i = 0; i < count.size() - 1; i++) {
            for(int j = i + 1; j < count.size(); j++) {
                answer += (count.get(i) * count.get(j));
            }
        }
        return answer;
    }
}
