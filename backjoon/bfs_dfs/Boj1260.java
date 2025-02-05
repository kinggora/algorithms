package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1260 {

    private static List<ArrayList<Integer>> edges;
    private static boolean[] visited;
    private static Queue<Integer> queue;
    private static StringBuilder sb;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수
        int V = Integer.parseInt(st.nextToken()); // 시작 정점

        edges = new ArrayList<>();
        edges.add(null);
        for(int i = 1; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        // 오름차순 정렬
        for(int i = 1; i < edges.size(); i++) {
            Collections.sort(edges.get(i));
        }

        sb = new StringBuilder();
        visited = new boolean[N + 1];
        dfs(V);

        sb.append("\n");

        visited = new boolean[N + 1];
        queue = new LinkedList<>();
        bfs(V);

        System.out.print(sb);
    }

    static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(" ");
        for(int v : edges.get(start)) {
            if(!visited[v]) {
                dfs(v);
            }
        }
    }

    static void bfs(int start) {
        visited[start] = true;
        queue.add(start);
        sb.append(start).append(" ");
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for(int v : edges.get(u)) {
                if(!visited[v]) {
                    visited[v] = true;
                    sb.append(v).append(" ");
                    queue.add(v);
                }
            }
        }
    }
}
