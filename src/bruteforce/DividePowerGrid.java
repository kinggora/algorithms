package bruteforce;

import java.util.ArrayList;
import java.util.List;

public class DividePowerGrid {

    public static void main(String[] args) {
        DividePowerGrid d = new DividePowerGrid();
        int n1 = 9;
        int[][] wires1 = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        System.out.println(d.solution(n1, wires1)); // 3
        int n2 = 4;
        int[][] wires2 = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(d.solution(n2, wires2)); // 0
        int n3 = 7;
        int[][] wires3 = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};
        System.out.println(d.solution(n3, wires3)); // 1
    }

    // 트리 형태의 전선 중 하나를 끊어 네트워크를 2개로 분할
    // 두 전력망이 갖게되는 송전탑 개수를 최대한 비슷하게 (차이가 최소)
    // n: 송전탑 개수
    // wires: 전선 정보
    // 연결된 두 점에서 dfs
    List<List<Integer>> edges = new ArrayList<>();

    public int solution(int n, int[][] wires) {
        edges.clear();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] wire : wires) {
            edges.get(wire[0]).add(wire[1]);
            edges.get(wire[1]).add(wire[0]);
        }
        int min = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];
            visited = new boolean[n + 1];
            visited[u] = visited[v] = true;
            dfs(u);
            int cntU = count;
            dfs(v);
            int cntV = count - cntU;
            min = Math.min(min, Math.abs(cntU - cntV));
        }
        return min;
    }
    int count = 0;
    boolean[] visited;
    public void dfs(int start) {
        for(int v : edges.get(start)) {
            if(!visited[v]) {
                visited[v] = true;
                count++;
                dfs(v);
            }
        }
    }
}
