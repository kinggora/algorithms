package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj24444 {

    private static List<ArrayList<Integer>> edges;
    private static int cnt;
    private static int[] visited;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        edges.add(null); // index 0
        for(int i = 1; i <= N; i++) { // index 1 ~ N
            edges.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        // 오름차 순 정렬
        for(int i = 1; i <= N; i++) {
            Collections.sort(edges.get(i));
        }

        cnt = 1;
        visited = new int[N + 1];
        queue = new LinkedList<>();

        bfs(R);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int start) {
        visited[start] = cnt++;
        queue.add(start);
        while(!queue.isEmpty()) {
            int u = queue.remove();
            for(int v : edges.get(u)) {
                if(visited[v] == 0) {
                    visited[v] = cnt++;
                    queue.add(v);
                }
            }
        }
    }
}
