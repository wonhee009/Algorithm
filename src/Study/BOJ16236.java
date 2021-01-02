package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
    static int N;
    static int[][] map;
    static SharkBabyIndex shark;
    static int[] fishNumber = new int[7];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 9) {
                    shark = new SharkBabyIndex(i, j, 0, 2);
                    continue;
                }
                if(temp != 0) {
                    map[i][j] = temp;
                    fishNumber[temp]++;
                    continue;
                }
            }
        }

        int time = 0;
        // 상어보다 작은 크기의 물고기가 있을때 까지
        while(checkFish()) {
            int len = moveShark();
            if(len < 0) {
                break;
            }
            time += len;
        }
        System.out.println(time);
    }

    public static boolean checkFish() {

        int min = Math.min(6, shark.size);

        for(int i = min; i > 0; i--) {
            if(fishNumber[i] > 0) {
                return true;
            }
        }
        return false;
    }

    static int[] dirI = {0, 0, 1, -1};
    static int[] dirJ = {1, -1, 0, 0};
    public static int moveShark() {
        boolean[][] visit = new boolean[N][N];
        int len = 0;
        Queue<SharkBabyIndex> q = new LinkedList<>();
        q.add(shark);
        visit[shark.i][shark.j] = true;
        while(!q.isEmpty()) {
            int size = q.size();

            SharkBabyIndex nextShark = null;
            for(int i = 0; i < size; i++) {
                SharkBabyIndex temp = q.poll();
                if(map[temp.i][temp.j] != 0 && map[temp.i][temp.j] < temp.size ) {
                    if(nextShark == null) {
                        nextShark = temp;
                        continue;
                    }
                    else {
                        if(temp.i < nextShark.i) {
                            nextShark = temp;
                            q.add(nextShark);
                            continue;
                        }
                        else if(nextShark.i == temp.i) {
                            if(temp.j < nextShark.j) {
                                nextShark = temp;
                                q.add(nextShark);
                                continue;
                            }
                        }
                    }
                }
                q.add(temp);
            }
            if(nextShark != null) {
                fishNumber[map[nextShark.i][nextShark.j]]--;
                map[nextShark.i][nextShark.j] = 0;
                nextShark.count++;
                if(nextShark.count == nextShark.size) {
                    nextShark.size++;
                    nextShark.count = 0;
                }
                shark = nextShark;
                return len;
            }

            for(int i = 0; i < size; i++) {
                SharkBabyIndex temp = q.poll();

                for(int index = 0; index < 4; index++) {
                    int nextI = temp.i + dirI[index];
                    int nextJ = temp.j +  dirJ[index];

                    if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N || map[nextI][nextJ] > temp.size || visit[nextI][nextJ]) {
                        continue;
                    }
                    visit[nextI][nextJ] = true;
                    q.add(new SharkBabyIndex(nextI, nextJ, temp.count, temp.size));
                }
            }

            len++;
        }

        return -1;
    }
}

class SharkBabyIndex {
    int i;
    int j;
    int count;
    int size;

    SharkBabyIndex(int i, int j, int count, int size) {
        this.i = i;
        this.j = j;
        this.count = count;
        this.size = size;
    }
}