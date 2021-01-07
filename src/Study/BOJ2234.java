package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2234 {
    static int m, n;
    static int[][] map;
    static HashMap<Integer, Integer> roomCount = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m][n];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkRoom();
        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                max = Math.max(max, deleteWall(i, j));
            }
        }
        System.out.println(max);
    }

    static int[][] room;
    public static void checkRoom() {
        room = new int[m][n];

        int roomNum = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (room[i][j] != 0) {
                    continue;
                }

                checkRoomNumber(roomNum, i, j);
                roomNum++;
            }
        }
        System.out.println(roomCount.size());
        getMax();
    }

    static int[] dirI = {0, -1, 0, 1};
    static int[] dirJ = {-1, 0, 1, 0};
    public static void checkRoomNumber(int number, int i, int j) {
        Queue<RoomIndex> q = new LinkedList<>();
        q.add(new RoomIndex(i, j));
        int count = 0;

        while(!q.isEmpty()) {
            RoomIndex temp = q.poll();
            room[temp.i][temp.j] = number;
            count++;

            for(int index = 0; index < 4; index++) {
                if(((1 << index) & map[temp.i][temp.j]) != 0) {
                    continue;
                }

                int nextI = temp.i + dirI[index];
                int nextJ = temp.j + dirJ[index];

                if(room[nextI][nextJ] != 0) {
                    continue;
                }
                room[nextI][nextJ] = number;
                q.add(new RoomIndex(nextI, nextJ));
            }
        }
        roomCount.put(number, count);
    }
    public static void getMax() {
        int count = roomCount.size();
        int max = 0;
        for(int i = 1; i <= count; i++) {
            max = Math.max(max, roomCount.get(i));
        }
        System.out.println(max);
    }

    public static int deleteWall(int i, int j) {
        int max = 0;
        for(int index = 0; index < 4; index++) {
            if(((1 << index) & map[i][j]) == 0) {
                continue;
            }

            int nextI = i + dirI[index];
            int nextJ = j + dirJ[index];

            if(nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || room[i][j] == room[nextI][nextJ]) {
                continue;
            }

            max = Math.max(max, roomCount.get(room[i][j]) + roomCount.get(room[nextI][nextJ]));
        }
        return max;
    }
}

class RoomIndex {
     int i;
     int j;

     RoomIndex(int i, int j) {
         this.i = i;
         this.j = j;
     }
}
