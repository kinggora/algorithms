package dfs_bfs;

import java.util.ArrayList;
import java.util.List;

public class Network {
    public static void main(String[] args) {
        int n1 = 3;
        int[][] computers1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int n2 = 3;
        int[][] computers2 = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        Network n = new Network();
        System.out.println(n.solution(n1, computers1)); // 2
        System.out.println(n.solution(n2, computers2)); // 1
    }

    // A -> B
    // B -> C
    // A, B, C 한 네트워크
    boolean[] visited;
    List<List<Integer>> connections;
    public int solution(int n, int[][] computers) {
        visited = new boolean[computers.length];
        connections = new ArrayList<>();
        for(int i = 0; i < computers.length; i++) {
            List<Integer> conn = new ArrayList<>();
            // j: 컴퓨터 i와 연결된 컴퓨터
            for(int j = 0; j < computers[i].length; j++) {
                if(i != j && computers[i][j] == 1) {
                    conn.add(j);
                }
            }
            connections.add(conn);
        }
        int count = 0;
        for(int i = 0; i < computers.length; i++) {
            if(!visited[i]) {
                count++;
                dfs(i);
            }
        }
        return count;
    }

    private void dfs(int start) {
        visited[start] = true;
        for(int v : connections.get(start)) {
            if(!visited[v]) {
                dfs(v);
            }
        }
    }
}
