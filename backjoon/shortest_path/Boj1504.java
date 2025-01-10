package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1504 {

    private static final int MAX = Integer.MAX_VALUE;
    private static int N;
    private static int[] d1, d2;
    private static List<Node>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        d1 = new int[N+1]; // v1에서 다른 정점까지의 최단 경로
        d2 = new int[N+1]; // v2에서 다른 정점까지의 최단 경로
        edges = new List[N+1]; // edges[i]: i에서 v로 가는 가중치 w의 간선 리스트 (Node(v,w))

        // 최단 경로를 MAX 값으로 초기화
        for(int i = 1; i <= N; i++) {
            d1[i] = MAX;
            d2[i] = MAX;
            edges[i] = new ArrayList<>();
        }

        // 양방향 간선 정보
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Node(b, c));
            edges[b].add(new Node(a, c));
        }

        // 반드시 거쳐야 하는 두 정점 (v1, v2)
        st = new StringTokenizer(reader.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        dijkstra(v1, d1);
        dijkstra(v2, d2);

        // 1 -> v1 -> v2 -> N
        int path1 = MAX;
        if(d1[1] != MAX && d1[v2] != MAX && d2[N] != MAX) {
            path1 = d1[1] + d1[v2] + d2[N];
        }

        // 1 -> v2 -> v1 -> N
        int path2 = MAX;
        if(d2[1] != MAX && d2[v1] != MAX && d1[N] != MAX) {
            path2 = d2[1] + d2[v1] + d1[N];
        }

        int result = Math.min(path1, path2);
        System.out.print(result == MAX ? -1 : result);
    }

    static void dijkstra(int start, int[] dist) {
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getW() - o2.getW();
            }
        });

        boolean[] visited = new boolean[N + 1];
        dist[start] = 0;
        queue.add(new Node(start, dist[start]));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int u = now.getV();
            visited[u] = true;
            for(Node next : edges[u]) {
                int v = next.getV();
                if(!visited[v]) {
                    int w = next.getW();
                    if(dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        queue.add(new Node(v, dist[v]));
                    }
                }
            }
        }
    }

    static class Node {
        private final int v;
        private final int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }
    }
}
