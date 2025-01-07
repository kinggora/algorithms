package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1753 {

    private static final int MAX = Integer.MAX_VALUE;
    private static int[] d;
    private static boolean[] visited;
    private static List<Node>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        int K = Integer.parseInt(reader.readLine()); // 시작 정점
        d = new int[V+1]; // K에서 다른 정점까지의 최단 경로
        visited = new boolean[V+1];
        edges = new List[V+1]; // edges[i]: 정점 i에서 v로 가는 가중치 w의 간선 리스트 (Node(v,w))

        // 최단 경로를 MAX 값으로 초기화
        for(int i = 1; i <= V; i++) {
            d[i] = MAX;
            edges[i] = new ArrayList<>();
        }

        // 간선 정보 초기화
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[u].add(new Node(v, w));
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++) {
            if(d[i] == MAX) {
                sb.append("INF\n");
            } else {
                sb.append(d[i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void dijkstra(int start) {
        // 우선순위 큐의 요소(Node)를 가중치(w)에 대해 오름차 순으로 정렬
        // poll() 시 가중치가 최소인 요소를 반환
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getW() - o2.getW();
            }
        });
        d[start] = 0;
        queue.add(new Node(start, d[start]));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int u = now.getV();
            visited[u] = true;
            for(Node next : edges[u]) {
                int v = next.getV();
                if(!visited[v]) {
                    int w = next.getW();
                    if(d[u] + w < d[v]) {
                        d[v] = d[u] + w;
                        queue.add(new Node(v, d[v]));
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
