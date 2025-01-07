package dfs_bfs;

public class TargetNumber {

    public static void main(String[] args) {
        TargetNumber n = new TargetNumber();
        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;

        System.out.println(n.solution(numbers1, target1)); // 5
        System.out.println(n.solution(numbers2, target2)); // 2
    }

    // n개의 양수 (1 이상 1000 이하)
    public int solution(int[] numbers, int target) {
        visited = new boolean[numbers.length];
        dfs(numbers, target, 0, 0);
        return count;
    }

    int count = 0;
    boolean[] visited;
    public void dfs(int[] numbers, int target, int depth, int n) {
        if(depth == numbers.length) {
            if(n == target) {
                count++;
            }
            return;
        }
        if(!visited[depth]) {
            visited[depth] = true;
            dfs(numbers, target, depth + 1, n + numbers[depth]);
            dfs(numbers, target, depth + 1, n + (-1) * numbers[depth]);
            visited[depth] = false;
        }
    }

}
