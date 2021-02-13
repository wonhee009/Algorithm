package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ8972 {
    static class ArduinoIndex {
        int i;
        int j;
        ArduinoIndex(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int R, C;
    static int[][] map;
    static Queue<ArduinoIndex> mad = new LinkedList<>();
    static ArduinoIndex jongsu;
    static int[] dirI = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dirJ = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for(int i = 0; i < R; i++) {
            String temp = br.readLine();
            for(int j = 0; j < C; j++) {
                char t = temp.charAt(j);
                if(t == 'R') {
                    map[i][j] = -1;
                    mad.add(new ArduinoIndex(i, j));
                } else if(t == 'I') {
                    jongsu = new ArduinoIndex(i, j);
                }
            }
        }

        String order = br.readLine();
        boolean flag = true;
        for(int orderIndex = 0; orderIndex < order.length(); orderIndex++) {
            int index = order.charAt(orderIndex) - '0';
            if(!moveJongsu(index)) {
                System.out.println("kraj " + (orderIndex + 1));
                flag = false;
                break;
            }
            if(!moveArduino()) {
                System.out.println("kraj " + (orderIndex + 1));
                flag = false;
                break;
            }
        }
        if(flag) {
            char[][] map = new char[R][C];
            for(int i = 0; i < R; i++) {
                Arrays.fill(map[i], '.');
            }
            map[jongsu.i][jongsu.j] = 'I';
            while(!mad.isEmpty()) {
                ArduinoIndex temp = mad.poll();
                map[temp.i][temp.j] = 'R';
            }
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static boolean moveJongsu(int index) {
        int nextI = jongsu.i + dirI[index];
        int nextJ = jongsu.j + dirJ[index];

        if(map[nextI][nextJ] == -1) {
            return false;
        }
        jongsu.i = nextI;
        jongsu.j = nextJ;
        return true;
    }

    public static boolean moveArduino() {
        int[][] arduinoMap = new int[R][C];
        while(!mad.isEmpty()) {
            ArduinoIndex temp = mad.poll();
            map[temp.i][temp.j] = 0;
            int minI = temp.i;
            int minJ = temp.j;
            int minDistance = Integer.MAX_VALUE;
            for(int index = 1; index < 10; index++) {
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];
                if(nextI < 0 || nextI >= R || nextJ < 0 || nextJ >= C) {
                    continue;
                }
                int nextDistance = Math.abs(nextI - jongsu.i) + Math.abs(nextJ - jongsu.j);
                if(nextDistance < minDistance) {
                    minI = nextI;
                    minJ = nextJ;
                    minDistance = nextDistance;
                }
            }
            if((minI == jongsu.i) && (minJ == jongsu.j)) {
                return false;
            }
            arduinoMap[minI][minJ]++;
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(arduinoMap[i][j] == 1) {
                    mad.add(new ArduinoIndex(i, j));
                    map[i][j] = -1;
                }
            }
        }
        return true;
    }
}
