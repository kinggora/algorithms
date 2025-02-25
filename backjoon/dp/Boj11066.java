package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11066 {

    static int[] dp;
    static int[][] min;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine());
            StringTokenizer st = new StringTokenizer(reader.readLine());
            dp = new int[N + 1];
            min = new int[N + 1][N + 1];
            for(int j = 1; j <= N; j++) {
                dp[j] = dp[j - 1] + Integer.parseInt(st.nextToken());
            }
            sb.append(dp(0, N)).append("\n");
        }
        System.out.print(sb);
    }

    public static int dp(int x, int y) {
        if(x + 1 == y) {
            return 0;
        }
        if(min[x][y] == 0) {
            min[x][y] = Integer.MAX_VALUE;
            for(int i = x + 1; i <= y - 1; i++) {
                min[x][y] = Math.min(min[x][y], dp(x, i) + dp(i, y));
            }
            min[x][y] += dp[y] - dp[x];
        }
        return min[x][y];
    }

}
