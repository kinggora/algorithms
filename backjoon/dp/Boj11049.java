package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11049 {

    static int[][] cnt;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        matrix = new int[N][N];
        cnt = new int[N][N];
        for(int i = 0; i < N; i++) {
            String[] split = reader.readLine().split(" ");
            matrix[i][0] = Integer.parseInt(split[0]);
            matrix[i][1] = Integer.parseInt(split[1]);
        }
        System.out.print(dp(0, N - 1));
    }

    public static int dp(int x, int y) {
        if(x == y) {
            return 0;
        }
        if(cnt[x][y] == 0) {
            cnt[x][y] = Integer.MAX_VALUE;
            for(int i = x; i < y; i++) {
                cnt[x][y] = Math.min(cnt[x][y], dp(x, i) + dp(i + 1, y) + matrix[x][0] * matrix[i][1] * matrix[y][1]);
            }
        }
        return cnt[x][y];
    }

}
