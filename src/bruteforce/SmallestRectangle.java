package bruteforce;

import java.util.Arrays;

public class SmallestRectangle {
    public static void main(String[] args) {
        int[][] size1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int[][] size2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        int[][] size3 = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};

        SmallestRectangle s = new SmallestRectangle();
        System.out.println(s.solution(size1)); // 4000
        System.out.println(s.solution(size2)); // 120
        System.out.println(s.solution(size3)); // 133

    }

    public int solution(int[][] sizes) {
        int maxW = Integer.MIN_VALUE;
        int maxH = Integer.MIN_VALUE;
        for (int i = 0; i < sizes.length; i++) {
            Arrays.sort(sizes[i]);
            maxW = Math.max(maxW, sizes[i][0]);
            maxH = Math.max(maxH, sizes[i][1]);
        }
        return maxW * maxH;
    }
}
