package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1697 {

    private static boolean[] visited;
    private static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split(" ");
        int posA = Integer.parseInt(str[0]);
        int posB = Integer.parseInt(str[1]);
        visited = new boolean[100001];
        map = new int[100001];

        bfs(posA);
        System.out.println(map[posB]);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()) {
            int pos = queue.remove();

            // 현재 위치(pos)에서 이동 가능한 위치 탐색
            int nextPos = pos + 1;
            if(isNextPos(nextPos)) {
                queue.add(nextPos);
                visited[nextPos] = true;
                map[nextPos] = map[pos] + 1;
            }
            nextPos = pos - 1;
            if(isNextPos(nextPos)) {
                queue.add(nextPos);
                visited[nextPos] = true;
                map[nextPos] = map[pos] + 1;
            }
            nextPos = 2 * pos;
            if(isNextPos(nextPos)) {
                queue.add(nextPos);
                visited[nextPos] = true;
                map[nextPos] = map[pos] + 1;
            }
        }
    }

    private static boolean isNextPos(int pos) {
        return pos >= 0 && pos < map.length && !visited[pos] && map[pos] == 0;
    }
}
