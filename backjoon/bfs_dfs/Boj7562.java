package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj7562 {

    private static final int[] dx = { -1, -1, -2, -2, 1, 1, 2, 2};
    private static final int[] dy = { -2, 2, -1, 1, -2, 2, -1, 1};
    private static boolean[][] visited;
    private static int[][] chessboard;
    private static int l;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            l = Integer.parseInt(reader.readLine()); // 체스판 한 변의 길이

            StringTokenizer st = new StringTokenizer(reader.readLine());
            // 현재 나이트의 위치
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(reader.readLine());
            // 나이트가 이동하려는 위치
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            visited = new boolean[l][l];
            chessboard = new int[l][l];

            bfs(startX, startY);
            sb.append(chessboard[endX][endY]).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int startX, int startY) {
        Queue<Integer> qX = new LinkedList<>();
        Queue<Integer> qY = new LinkedList<>();
        qX.add(startX);
        qY.add(startY);
        visited[startX][startY] = true;

        while(!qX.isEmpty() && !qY.isEmpty()) {
            int x = qX.remove();
            int y = qY.remove();

            // 현재 위치 (x,y)에서 이동 가능한 위치 (nextX, nextY) 탐색
            for(int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if(isNextPos(nextX, nextY)) {
                    qX.add(nextX);
                    qY.add(nextY);
                    visited[nextX][nextY] = true;
                    chessboard[nextX][nextY] = chessboard[x][y] + 1;
                }
            }
        }
    }

    private static boolean isNextPos(int x, int y) {
        return x >= 0 && y >= 0 && x < l && y < l && !visited[x][y] && chessboard[x][y] == 0;
    }
}
