package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1956 {
    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int V = Integer.parseInt(st.nextToken()); // 마을의 개수
        int E = Integer.parseInt(st.nextToken()); // 도로의 개수
        int[][] dest = new int[V+1][V+1];

        // 최단 경로 배열 초기화
        for(int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {
                if(i == j) {
                    dest[i][j] = 0;
                } else {
                    dest[i][j] = MAX;
                }

            }
        }

        // 도로 정보 입력
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(reader.readLine());
            // a번 마을에서 b번 마을로 가는 거리가 c인 도로
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dest[a][b] = c;
        }

        // 플로이드 워셜 알고리즘: 모든 정점 간의 최단 경로 구하기
        for(int k = 1; k <= V; k++) {
            for(int i = 1; i <= V; i++) {
                for(int j = 1; j <= V; j++) {
                    if(dest[i][k] != MAX && dest[k][j] != MAX) {
                        dest[i][j] = Math.min(dest[i][j], dest[i][k] + dest[k][j]);
                    }
                }
            }
        }

        // 최단 거리 사이클 구하기
        // i -> ... -> j -> ... -> i
        int min = MAX;
        for(int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {
                if(i == j) {
                    continue;
                }
                if(dest[i][j] != MAX && dest[j][i] != MAX) {
                    min = Math.min(min, dest[i][j] + dest[j][i]);
                }
            }
        }
        if(min == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}
