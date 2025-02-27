package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1520 {

    static int[][] map;
    static Integer[][] dp;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        dp = new Integer[row][col];
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(reader.readLine());
            for(int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1;
        System.out.println(dp(row - 1, col - 1));
    }

    static int dp (int x, int y) {
        if(dp[x][y] == null) {
            dp[x][y] = 0;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx > -1 && ny > -1 && nx < map.length && ny < map[0].length) {
                    if(map[nx][ny] > map[x][y]) {
                        dp[x][y] += dp(nx, ny);
                    }
                }
            }
        }
        return dp[x][y];
    }

}
