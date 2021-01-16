package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class BOJ10800 {
    public static class ball implements Comparable<ball> {
        int who;
        int color;
        int size;

        public ball(int who, int color, int size) {
            super();
            this.who = who;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(ball o) {
            // TODO Auto-generated method stub
            return this.size - o.size;
        }
    }

    static long sum[];
    static long resum;
    static ball num[];
    static long result[];
    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sum = new long[n+1];
        result = new long[n+1];
        num = new ball[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            num[i] = new ball(i, c, s);
            sum[c] += s;
            resum+=s;
        }
        Arrays.sort(num);

        int i = n - 1;
        while (i > 0) {
            Queue<ball> q = new LinkedList<ball>();
            q.add(num[i]);
            sum[num[i].color]-=num[i].size;
            resum-=num[i].size;
            while(i>0&&q.peek().size==num[i-1].size) {
                q.add(num[i-1]);
                sum[num[i-1].color]-=num[i-1].size;
                resum-=num[i-1].size;
                i--;
            }
            i--;
            while(!q.isEmpty()) {
                ball next=q.remove();
                result[next.who]=resum-sum[next.color];
            }
        }
        StringBuilder sb=new StringBuilder();
        for(i=0;i<n;i++) {
            sb.append(result[i]);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
