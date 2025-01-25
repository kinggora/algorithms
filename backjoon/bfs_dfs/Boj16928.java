package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16928 {

    private static final int[] board = new int[100]; // 10 X 10 (100칸);
    private final static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사다리 수
        int M = Integer.parseInt(st.nextToken()); // 뱀 수

        // 사다리와 뱀의 위치 저장
        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map.put(x, y);
        }

        bfs(0);
        System.out.println(board[99]);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int pos = queue.remove();
            // 현재 위치(pos)에서 이동 가능한 위치(nextPos) 탐색
            // 주사위에서 나올 수 있는 수: 1 ~ 6
            for(int i = 1; i <= 6; i++) {
                int nextPos = pos + i;
                if(nextPos < 0 || nextPos > 99) {
                    continue;
                }
                // 주사위로 이동한 위치에 사다리나 뱀이 있는지 확인
                if(map.containsKey(nextPos)) {
                    nextPos = map.get(nextPos);
                }
                if(board[nextPos] == 0) {
                    board[nextPos] = board[pos] + 1;
                    queue.add(nextPos);
                }
            }
        }
    }
}
