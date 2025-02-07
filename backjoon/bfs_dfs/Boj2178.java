package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2178 {
    private static final int[] dx = { -1, 1, 0, 0};
    private static final int[] dy = { 0, 0, -1, 1};
    private static boolean[][] visited;
    private static int[][] map;
    private static Queue<Integer> qX;
    private static Queue<Integer> qY;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String line = reader.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        qX = new LinkedList<>();
        qY = new LinkedList<>();
        // 출발 위치 (0, 0)
        bfs(0, 0);
        // 도착 위치에 누적된 분기 레벨 반환
        System.out.println(map[N-1][M-1]);
    }

    static void bfs(int startX, int startY) {
        qX.add(startX);
        qY.add(startY);
        visited[startX][startY] = true;
        while(!qX.isEmpty() && !qY.isEmpty()) {
            int x = qX.remove();
            int y = qY.remove();
            // 현재 위치 (x,y)에서 인접한 칸 탐색
            for(int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length) {
                    continue;
                }
                if(map[nextX][nextY] == 0 || visited[nextX][nextY]) {
                    continue;
                }
                qX.add(nextX);
                qY.add(nextY);
                visited[nextX][nextY] = true;
                map[nextX][nextY] += map[x][y]; // 다음 위치 값(1)에 현재 위치 값 누적
                // map[nextX][nextY] = map[x][y] + 1;
            }
        }
    }
}
