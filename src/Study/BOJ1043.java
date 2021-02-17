package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1043 {
    static int N, M;
    static boolean[] know;
    static boolean[][] connect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        know = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        int liarCount = Integer.parseInt(st.nextToken());
        if(liarCount == 0) {
            System.out.println(M);
            return;
        }
        for(int i = 0; i < liarCount; i++) {
            know[Integer.parseInt(st.nextToken())] = true;
        }
        connect = new boolean[N + 1][N + 1];
        int[][] party = new int[M][];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyCount = Integer.parseInt(st.nextToken());
            party[i] = new int[partyCount];
            party[i][0] = Integer.parseInt(st.nextToken());
            for(int j = 1; j < partyCount; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
                connect[party[i][j - 1]][party[i][j]] = connect[party[i][j]][party[i][j - 1]] = true;
            }
        }

        for(int i = 1; i <= N; i++) {
            if (know[i]) {
                dfs(i);
            }
        }

        int answer = 0;
        for(int i = 0; i < M; i++) {
            if(!know[party[i][0]]) {
                answer++;
            }
        }
        System.out.println(answer);
    }
    public static void dfs(int n) {
        for(int i = 1; i <= N; i++) {
            if(connect[n][i] && !know[i]) {
                know[i] = true;
                dfs(i);
            }
        }
    }
}
