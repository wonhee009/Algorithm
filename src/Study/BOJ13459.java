package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13459 {
    static int r, c;
    static char map[][];
    static char target = 'O';
    static char wall = '#';
    static Queue<BallIndex> redQ = new LinkedList<>();
    static Queue<BallIndex> blueQ = new LinkedList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for(int i = 0; i < r; i++) {
            String temp = br.readLine();
            for(int j = 0; j < c; j++) {
                char t= temp.charAt(j);
                if(t == 'R') {
                    redQ.add(new BallIndex(i, j));
                    continue;
                }
                if(t == 'B') {
                    blueQ.add(new BallIndex(i, j));
                    continue;
                }
                map[i][j] = t;
            }
        }

        int count = 0;
        while(!redQ.isEmpty()) {
            if(count >= 10) {
                answer = 0;
                break;
            }
            int len = redQ.size();
            for(int i = 0; i < len; i++) {
                move();
                if(answer == 1) {
                    break;
                }
            }
            if(answer == 1) {
                break;
            }
            count++;
        }
        System.out.println(answer);
    }

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static void move() {
        BallIndex curRed = redQ.poll();
        BallIndex curBlue = blueQ.poll();

        for(int i = 0; i < 4; i++) {
            BallIndex tempRed = new BallIndex(curRed.i, curRed.j);
            BallIndex tempBlue = new BallIndex(curBlue.i, curBlue.j);
            switch (i) {
                case 0:
                    if(curRed.j > curBlue.j) {
                        tempRed = moveRed(tempRed, tempBlue, i);
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                    }
                    else {
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                        tempRed = moveRed(tempRed, tempBlue, i);
                    }
                    break;
                case 1:
                    if(curRed.j < curBlue.j) {
                        tempRed = moveRed(tempRed, tempBlue, i);
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                    }
                    else {
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                        tempRed = moveRed(tempRed, tempBlue, i);
                    }
                    break;
                case 2:
                    if(curRed.i > curBlue.i) {
                        tempRed = moveRed(tempRed, tempBlue, i);
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                    }
                    else {
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                        tempRed = moveRed(tempRed, tempBlue, i);
                    }
                    break;
                case 3:
                    if(curRed.i < curBlue.i) {
                        tempRed = moveRed(tempRed, tempBlue, i);
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                    }
                    else {
                        tempBlue = moveBlue(tempRed, tempBlue, i);
                        tempRed = moveRed(tempRed, tempBlue, i);
                    }
                    break;
            }

            if(map[tempBlue.i][tempBlue.j] == target) {
                continue;
            }
            if(map[tempRed.i][tempRed.j] == target) {
                answer = 1;
                return;
            }
            if(curRed.i == tempRed.i && curRed.j == tempRed.j && curBlue.j == tempBlue.j && curBlue.i == tempBlue.i) {
                continue;
            }
            redQ.add(tempRed);
            blueQ.add(tempBlue);
        }
    }

    public static BallIndex moveRed(BallIndex red, BallIndex blue, int index) {
        while(true) {
            red.i += dirI[index];
            red.j += dirJ[index];
            if(map[red.i][red.j] == target) {
                return red;
            }
            if(map[red.i][red.j] == wall || (red.i == blue.i && red.j == blue.j)) {
                red.i -= dirI[index];
                red.j -= dirJ[index];
                return red;
            }
        }
    }

    public static BallIndex moveBlue(BallIndex red, BallIndex blue, int index) {
        while(true) {
            blue.i += dirI[index];
            blue.j += dirJ[index];
            if(map[blue.i][blue.j] == target) {
                return blue;
            }
            if(map[blue.i][blue.j] == wall || (red.i == blue.i && red.j == blue.j)) {
                blue.i -= dirI[index];
                blue.j -= dirJ[index];
                return blue;
            }
        }
    }
}

class BallIndex {
    int i;
    int j;

    BallIndex(int i, int j) {
        this.i = i;
        this.j = j;
    }
}