package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2606 {

    private static List<ArrayList<Integer>> edges;
    private static int cnt = 0;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int E = Integer.parseInt(reader.readLine());

        edges = new ArrayList<>();
        edges.add(null);
        for(int i = 1; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        visited = new boolean[N + 1];

        dfs(1); // 1번에서 시작

        System.out.print(cnt);
    }

    static void dfs(int start) {
        visited[start] = true;
        for(int v : edges.get(start)) {
            if(!visited[v]) {
                dfs(v);
                cnt++;
            }
        }
    }
}
