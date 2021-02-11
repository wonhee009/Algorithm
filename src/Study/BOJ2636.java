package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    static int row, col;
    static int[][] cheeseMap;
    static boolean[][] airMap;
    static class Cheese {
        int i;
        int j;

        Cheese(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int second = 1;
    static int lastCheese = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        cheeseMap = new int[row][col];
        airMap = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++) {
                cheeseMap[i][j] = Integer.parseInt(st.nextToken());
                if(cheeseMap[i][j] == 1) {
                    lastCheese++;
                }
            }
        }
        choiceAir();
        while(choiceEdge()) {
            second++;
            choiceAir();
        }
        System.out.println(second);
        System.out.print(lastCheese);
    }

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void choiceAir() {
        for(int i = 0; i < row; i++) {
            Arrays.fill(airMap[i], false);
        }
        Queue<Cheese> q = new LinkedList<>();
        q.add(new Cheese(0, 0));
        airMap[0][0] = true;
        while(!q.isEmpty()) {
            Cheese temp = q.poll();
            for(int index = 0; index < 4; index++){
                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];
                if(nextI < 0 || nextI >= row || nextJ < 0 || nextJ >= col) {
                    continue;
                }
                if(airMap[nextI][nextJ] || cheeseMap[nextI][nextJ] == 1) {
                    continue;
                }
                airMap[nextI][nextJ] = true;
                q.add(new Cheese(nextI, nextJ));
            }
        }
    }
    public static boolean choiceEdge() {
        Queue<Cheese> q = new LinkedList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(cheeseMap[i][j] == 0) {
                    continue;
                }
                if(airMap[i + 1][j] || airMap[i - 1][j] || airMap[i][j + 1] || airMap[i][j - 1]) {
                    q.add(new Cheese(i, j));
                    continue;
                }
            }
        }

        int qSize = q.size();
        while(!q.isEmpty()) {
            Cheese temp = q.poll();
            cheeseMap[temp.i][temp.j] = 0;
            airMap[temp.i][temp.j] = true;
        }

        if(lastCheese - qSize <= 0) {
            return false;
        }
        lastCheese -= qSize;
        return true;
    }
}
