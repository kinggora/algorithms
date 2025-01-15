package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj11404 {
    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine()); // 도시의 개수
        int m = Integer.parseInt(reader.readLine()); // 버스의 개수
        int[][] cost = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) {
                    cost[i][j] = 0;
                } else {
                    cost[i][j] = MAX;
                }

            }
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken()); // 버스의 시작 도시
            int b = Integer.parseInt(st.nextToken()); // 버스의 도착 도시
            int c = Integer.parseInt(st.nextToken()); // 버스 탑승 비용
            if(c < cost[a][b]) {
                cost[a][b] = c;
            }
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(cost[i][k] != MAX && cost[k][j] != MAX) {
                        cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(cost[i][j] == MAX) {
                    sb.append(0);
                } else {
                    sb.append(cost[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
