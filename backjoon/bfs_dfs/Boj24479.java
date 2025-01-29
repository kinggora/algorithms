package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj24479 {

    private static List<ArrayList<Integer>> edges;
    private static int cnt;
    private static int[] visited;

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
        dfs(R);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int start) {
        visited[start] = cnt++;
        for(int dest : edges.get(start)) { // start와 연결된 정점(dest) 조회
            if(visited[dest] == 0) { // 순서 지정 X == 방문 X
                dfs(dest);
            }
        }
    }
}
