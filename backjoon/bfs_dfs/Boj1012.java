package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1012 {
    private static boolean[][] visited;
    private static boolean[][] map;
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로 길이
            N = Integer.parseInt(st.nextToken()); // 세로 길이
            int K = Integer.parseInt(st.nextToken()); // 배추가 심어진 위치의 개수
            map = new boolean[M][N];
            visited = new boolean[M][N];

            for(int j = 0; j < K; j++) {
                st = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = true;
            }

            int cnt = 0; // 지렁이 수
            for(int x = 0; x < M; x++) {
                for(int y = 0; y < N; y++) {
                    // 배추가 존재하고(map == true) 방문한 적 없으면(visited == false) 탐색
                    if(map[x][y] && !visited[x][y]) {
                        dfs(x, y);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        if(x > 0 && map[x-1][y] && !visited[x-1][y]) {
            dfs(x-1, y);
        }
        if(y > 0 && map[x][y-1] && !visited[x][y-1]) {
            dfs(x, y-1);
        }
        if(x+1 < M && map[x+1][y] && !visited[x+1][y]) {
            dfs(x+1, y);
        }
        if(y+1 < N && map[x][y+1] && !visited[x][y+1]) {
            dfs(x, y+1);
        }
    }
}
