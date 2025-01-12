package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj13549 {
    private static final int LENGTH = 100001;
    private static int[] t;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        // 시작 정점으로부터 다른 모든 정점에 도달하는데 걸리는 최단 시간
        t = new int[LENGTH];
        Arrays.fill(t, Integer.MAX_VALUE);
        System.out.println(dijkstra(N, K));
    }

    static int dijkstra(int start, int end) {
        // time(시작 정점에서 v까지 도달하는데 걸리는 시간)에 대해 오름차 순 정렬
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getTime() - o2.getTime();
            }
        });

        boolean[] visited = new boolean[LENGTH];
        t[start] = 0;
        queue.add(new Node(start, t[start]));
        int min = 0;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int pos = now.getPos();

            if(pos == end) {
                min = now.getTime();
                break;
            }
            visited[pos] = true;

            // 순간 이동
            // 위치: pos -> pos * 2
            // 시간: time -> time + 0
            int nextPos = 2 * pos;
            if(nextPos < LENGTH && !visited[nextPos] && t[pos] < t[nextPos]) {
                t[nextPos] = t[pos];
                queue.add(new Node(nextPos, t[nextPos]));
            }

            // 앞으로 걷기
            // 위치: pos -> pos + 1
            // 시간: time -> time + 1
            nextPos = pos + 1;
            if(nextPos < LENGTH && !visited[nextPos] && t[pos] + 1 < t[nextPos]) {
                t[nextPos] = t[pos] + 1;
                queue.add(new Node(nextPos, t[nextPos]));
            }

            // 뒤로 걷기
            // 위치: pos -> pos - 1
            // 시간: time -> time + 1
            nextPos = pos - 1;
            if(nextPos >= 0 && !visited[nextPos] && t[pos] + 1 < t[nextPos]) {
                t[nextPos] = t[pos] + 1;
                queue.add(new Node(nextPos, t[nextPos]));
            }
        }
        return min;
    }

    static class Node {
        private final int pos;
        private final int time;

        public Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        public int getPos() {
            return pos;
        }

        public int getTime() {
            return time;
        }
    }
}
