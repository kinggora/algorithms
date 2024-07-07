package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KthNumber {

    public static void main(String[] args) {
        KthNumber k = new KthNumber();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] solution = k.solution(array, commands);
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
    }

    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1];
            int k = commands[i][2] - 1;

            int[] subArr = new int[end - start];
            for (int j = start; j < end; j++) {
                subArr[j - start] = array[j];
            }
            Arrays.sort(subArr);
            answer.add(subArr[k]);
        }
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
