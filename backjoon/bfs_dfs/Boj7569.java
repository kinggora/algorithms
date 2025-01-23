package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj7569 {

    private static final int[] dx = { -1, 1, 0, 0, 0, 0};
    private static final int[] dy = { 0, 0, -1, 1, 0, 0};
    private static final int[] dz = { 0, 0, 0, 0, -1, 1};
    private static final Queue<Position> queue = new LinkedList<>();
    private static int M;
    private static int N;
    private static int H;
    private static int[][][] storage;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(st.nextToken()); // 상자 가로 칸의 수 (y)
        N = Integer.parseInt(st.nextToken()); // 상자 세로 칸의 수 (x)
        H = Integer.parseInt(st.nextToken()); // 쌓아올려지는 상자의 수 (z)
        storage = new int[N][M][H];
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(reader.readLine());
                for(int k = 0; k < M; k++) {
                    storage[j][k][i] = Integer.parseInt(st.nextToken());
                    // 시작 위치(익은 토마토)를 큐에 추가
                    if(storage[j][k][i] == 1) {
                        queue.add(new Position(j, k, i));
                    }
                }
            }
        }

        bfs();

        int max = 0;
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    if(storage[j][k][i] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, storage[j][k][i]);
                }
            }
        }
        System.out.println(max - 1);
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            Position pos = queue.remove();

            // 현재 위치 (x,y)에서 이동 가능한 위치 (nextX, nextY) 탐색
            for(int i = 0; i < dx.length; i++) {
                int nextX = pos.getX() + dx[i];
                int nextY = pos.getY() + dy[i];
                int nextZ = pos.getZ() + dz[i];
                if(nextX >= 0 && nextY >= 0 && nextZ >= 0 && nextX < N && nextY < M && nextZ < H) {
                    if(storage[nextX][nextY][nextZ] == 0) {
                        storage[nextX][nextY][nextZ] = storage[pos.getX()][pos.getY()][pos.getZ()] + 1;
                        queue.add(new Position(nextX, nextY, nextZ));
                    }
                }
            }
        }
    }

    static class Position {
        private final int x;
        private final int y;
        private final int z;

        public Position(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }
}
