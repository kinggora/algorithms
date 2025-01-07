package dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class GameMapShortestPath {
    public static void main(String[] args) {
        GameMapShortestPath g = new GameMapShortestPath();
        int[][] maps1 = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        };
        System.out.println(g.solution(maps1)); // 11
        int[][] maps2 = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,0},
                {0,0,0,0,1}
        };
        System.out.println(g.solution(maps2)); // -1
    }

    public int solution(int[][] maps) {
        bfs(maps, 0, 0);
        int result = maps[maps.length - 1][maps[0].length - 1];
        if(result == 1) {
            return -1;
        }
        return result;
    }

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public void bfs(int[][] maps, int startX, int startY) {
        Queue<Integer> qX = new LinkedList<>();
        Queue<Integer> qY = new LinkedList<>();
        qX.add(startX);
        qY.add(startY);
        while (!qX.isEmpty() && !qY.isEmpty()) {
            int x = qX.remove();
            int y = qY.remove();
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length) {
                    continue;
                }
                if(maps[nx][ny] == 1) {
                    maps[nx][ny] = maps[x][y] + 1;
                    qX.add(nx);
                    qY.add(ny);
                }
                if(nx == maps.length - 1 && ny == maps[0].length - 1) {
                    return;
                }
            }
        }
    }
}
