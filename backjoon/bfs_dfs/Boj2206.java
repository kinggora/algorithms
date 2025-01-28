package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2206 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int[][] map;
    private static boolean[][][] visited;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken()); // row
        M = Integer.parseInt(st.nextToken()); // col
        map = new int[N][M];
        //cnt = new int[N][M];
        visited = new boolean[2][N][M];

        for(int i = 0; i < N; i++) {
            String input = reader.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        bfs(0, 0);
    }

    static void bfs(int startX, int startY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 1, false));
        visited[0][startX][startY] = true;
        while(!queue.isEmpty()) {
            Position pos = queue.remove();
            int x = pos.getX();
            int y = pos.getY();
            int cnt = pos.getCnt();
            boolean broke = pos.isBroke();

            if(x == N-1 && y == M-1) {
                System.out.println(cnt);
                return;
            }
            for(int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                int nextCnt = cnt + 1;
                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                // 다음 위치가 벽인 경우
                if(map[nextX][nextY] == 1) {
                    // 지금까지 벽을 부순 적 없고, 해당 벽을 부수고 방문한 이력이 없다면
                    if(!broke && !visited[1][nextX][nextY]) {
                        visited[1][nextX][nextY] = true;
                        queue.add(new Position(nextX, nextY, nextCnt, true));
                    }
                }
                // 다음 위치가 길일 경우
                else {
                    // 벽을 부순 적 있고, 벽을 부수고 다음 위치에 방문한 이력이 없다면
                    if(broke && !visited[1][nextX][nextY]) {
                        visited[1][nextX][nextY] = true;
                        queue.add(new Position(nextX, nextY, nextCnt, true));
                    }
                    // 벽을 부순 적 없고, 벽을 부수지 않고 다음 위치에 방문한 이력이 없다면
                    else if(!broke && !visited[0][nextX][nextY]) {
                        visited[0][nextX][nextY] = true;
                        queue.add(new Position(nextX, nextY, nextCnt, false));
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static class Position {
        private final int x;
        private final int y;
        private final int cnt;
        private final boolean broke;

        public Position(int x, int y, int cnt, boolean broke) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.broke = broke;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getCnt() {
            return this.cnt;
        }

        public boolean isBroke() {
            return this.broke;
        }
    }
}
