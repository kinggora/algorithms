package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj11657 {
    private static final long MAX = Long.MAX_VALUE;
    private static List<Node>[] road;
    private static long[] d;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 버스 노선의 개수
        road = new List[N+1];
        d = new long[N+1];
        for(int i = 1; i <= N; i++) {
            road[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(st.nextToken()); // 시작 도시
            int B = Integer.parseInt(st.nextToken()); // 도착 도시
            int C = Integer.parseInt(st.nextToken()); // 이동 시간
            road[A].add(new Node(B, C));
        }

        if(bellmanFord(1)) {
            for(int i = 2; i <= N; i++) {
                if(d[i] == MAX) {
                    System.out.println(-1);
                } else {
                    System.out.println(d[i]);
                }
            }
        } else {
            System.out.println(-1);
        }
    }

    static boolean bellmanFord(int start) {
        Arrays.fill(d, MAX);
        d[start] = 0; // 시작 노드의 최단 거리 값을 0으로 초기화
        for(int i = 0; i <= N; i++) { // 아래 과정을 N번 반복 (최단 경로 찾기: N-1번, 음수 순환 찾기: 1번)
            // 모든 간선 탐색
            for(int j = 1; j <= N; j++) { // j: 간선의 시작 노드
                for(Node node: road[j]) {
                    int nextPos = node.getPos(); // nextPos: 간선의 도착 노드
                    // 시작 노드(start)에서 j를 거쳐 nextPos로 가는 경로가 최단 거리일 경우
                    // start -> nextPos 의 최단 거리 값 갱신
                    if(d[j] != MAX && d[j] + node.getW() < d[nextPos]) {
                        if(i == N) { // N번째 루프에서 거리가 갱신되면 음수 순환 존재
                            return false;
                        }
                        d[nextPos] = d[j] + node.getW();
                    }
                }
            }

        }
        return true;
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
