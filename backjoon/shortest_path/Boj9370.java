package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj9370 {
    private static final int MAX = Integer.MAX_VALUE;
    private static List<Node>[] road;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken()); // 교차로 개수
            int m = Integer.parseInt(st.nextToken()); // 도로 개수
            int l = Integer.parseInt(st.nextToken()); // 목적지 후보 개수

            st = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(st.nextToken()); // 예술가들의 출발지
            // 예술가가 지나간 길 양 옆의 교차로
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            road = new List[n + 1];
            for(int j = 1; j <= n; j++) {
                road[j] = new ArrayList<>();
            }

            // 도로 정보
            for(int j = 0; j < m; j++) {
                st = new StringTokenizer(reader.readLine());
                // a와 b 사이에 길이 d의 양방양 도로
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                road[a].add(new Node(b, d));
                road[b].add(new Node(a, d));
            }

            int[] x = new int[l];
            // 목적지 후보
            for(int j = 0; j < l; j++) {
                x[j] = Integer.parseInt(reader.readLine());
            }
            Arrays.sort(x);

            for (int dest : x) {
                int path = dijkstra(s, dest); // s -> dest
                int path1 = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, dest); // s -> g h -> dest
                int path2 = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, dest);; // s -> h g -> dest

                if (Math.min(path1, path2) == path) {
                    sb.append(dest).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int dijkstra(int start, int end) {
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getW() - o2.getW();
            }
        });

        boolean[] visited = new boolean[n + 1];
        int[] d = new int[n + 1];
        Arrays.fill(d, MAX);
        d[start] = 0;
        queue.add(new Node(start, d[start]));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int pos = now.getPos();
            visited[pos] = true;
            for(Node next : road[pos]) {
                int nextPos = next.getPos();
                if(!visited[nextPos] && d[pos] + next.getW() < d[nextPos]) {
                    d[nextPos] = d[pos] + next.getW();
                    queue.add(new Node(nextPos, d[nextPos]));
                }
            }
        }
        return d[end];
    }

    static class Node {
        private final int pos;
        private final int w;

        public Node(int pos, int w) {
            this.pos = pos;
            this.w = w;
        }

        public int getPos() {
            return pos;
        }

        public int getW() {
            return w;
        }
    }
}
