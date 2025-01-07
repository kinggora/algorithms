package pccp;

import java.util.HashSet;
import java.util.Set;

public class OilDrilling {

    public static void main(String[] args) {
        OilDrilling o = new OilDrilling();
        int[][] rand1 = {
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}
        };
        int[][] rand2 = {
                {1, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}
        };
        System.out.println(o.solution(rand1)); // 9
        System.out.println(o.solution(rand2)); // 16

    }

    private int N;
    private int M;
    private int[][] markArea; // 구역을 마킹할 땅 (M x N)
    private int mark = 0; // 구역을 마킹할 넘버
    private int cnt; // 구역 넓이 계산


    // 열 단위로 행을 순회하며 석유가 존재할 경우 (land[x][y] == 1)
    // 마킹한 적 없는 땅이라면 (markArea == 0), dfs를 통해 구역을 마킹하며 넓이(cnt) 계산 -> 마킹 번호에 대한 넓이 저장
    // 해당 위치의 마킹 번호를 Set에 저장
    // markNum를 순회하며 총 넓이 계산
    public int solution(int[][] land) {
        M = land.length;
        N = land[0].length;
        markArea = new int[M][N];
        int[] area = new int[M * N]; // 구역 당 넓이

        int max = Integer.MIN_VALUE;
        for (int y = 0; y < N; y++) {
            Set<Integer> markNum = new HashSet<>();
            for (int x = 0; x < M; x++) {
                if(land[x][y] == 1) {
                    if(markArea[x][y] == 0) {
                        mark++;
                        cnt = 0;
                        dfs(land, x, y);
                        area[mark] = cnt;
                    }
                    markNum.add(markArea[x][y]);
                }
            }
            int sum = 0;
            for (int i : markNum) {
                sum += area[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    private void dfs(int[][] land, int x, int y) {
        markArea[x][y] = mark;
        cnt++;

        if(x > 0 && land[x - 1][y] == 1 && markArea[x - 1][y] == 0) {
            dfs(land, x - 1, y);
        }
        if(x + 1 < M && land[x + 1][y] == 1 && markArea[x + 1][y] == 0) {
            dfs(land, x + 1, y);
        }
        if(y > 0 && land[x][y - 1] == 1 && markArea[x][y - 1] == 0) {
            dfs(land, x, y - 1);
        }
        if(y + 1 < N && land[x][y + 1] == 1 && markArea[x][y + 1] == 0) {
            dfs(land, x, y + 1);
        }
    }


}
