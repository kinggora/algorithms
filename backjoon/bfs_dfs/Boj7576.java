package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj7576 {

    private static final int[] dx = { -1, 1, 0, 0};
    private static final int[] dy = { 0, 0, -1, 1};
    private static int[][] storage;
    private final static Queue<Integer> qX = new LinkedList<>();
    private final static Queue<Integer> qY = new LinkedList<>();
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        storage = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++) {
                storage[i][j] = Integer.parseInt(st.nextToken());
                // 시작 위치(익은 토마토)를 큐에 추가
                if(storage[i][j] == 1) {
                    qX.add(i);
                    qY.add(j);
                }
            }
        }

        bfs();

        int max = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 익지 않은 토마토가 하나라도 있다면 -1 반환
                if(storage[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, storage[i][j]);
            }
        }
        System.out.println(max - 1);
    }

    static void bfs() {
        while(!qX.isEmpty() && !qY.isEmpty()) {
            int x = qX.remove();
            int y = qY.remove();

            // 현재 위치 (x,y)에서 이동 가능한 위치 (nextX, nextY) 탐색
            for(int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
                    if(storage[nextX][nextY] == 0) {
                        storage[nextX][nextY] = storage[x][y] + 1;
                        qX.add(nextX);
                        qY.add(nextY);
                    }
                }
            }
        }
    }
}
