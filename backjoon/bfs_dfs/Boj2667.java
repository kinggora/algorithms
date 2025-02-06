package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2667 {

    private static int N;
    private static boolean[][] visited;
    private static boolean[][] map;
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        map = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            String line = reader.readLine();
            for(int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j) != '0';
            }
        }

        visited = new boolean[N][N];
        cnt = 0;
        List<Integer> results = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // 집이 존재하고(map == true) 방문한 적 없으면(visited == false) 탐색
                if(map[i][j] && !visited[i][j]) {
                    dfs(i, j);
                    results.add(cnt);
                    cnt = 0; // 단지 탐색에 사용했던 정적 변수 초기화
                }
            }
        }

        // 오름차순 정렬
        Collections.sort(results);
        System.out.println(results.size()); // 단지 수
        for(int n : results) {
            System.out.println(n); // 각 단지의 가구 수
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        cnt++;
        // (x,y)와 인접한 좌표: (x-1, y), (x, y-1), (x+1, y), (x, y+1)
        if(x > 0 && map[x-1][y] && !visited[x-1][y]) {
            dfs(x-1, y);
        }
        if(y > 0 && map[x][y-1] && !visited[x][y-1] ) {
            dfs(x, y-1);
        }
        if(x+1 < N && map[x+1][y] && !visited[x+1][y]) {
            dfs(x+1, y);
        }
        if(y+1 < N && map[x][y+1] && !visited[x][y+1]) {
            dfs(x, y+1);
        }
    }
}
