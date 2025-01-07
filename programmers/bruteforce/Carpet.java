package bruteforce;

import java.util.Arrays;
import java.util.Collections;

public class Carpet {

    public static void main(String[] args) {
        Carpet c = new Carpet();
        System.out.println(Arrays.toString(c.solution(10, 2)));
        System.out.println(Arrays.toString(c.solution(8, 1)));
        System.out.println(Arrays.toString(c.solution(24, 24)));
    }

    // 노란색: y/1 * 1, y/2 * 2, y/3 * 3, ...
    // 갈색: 2(y/1 + 1) + 4
    public int[] solution(int brown, int yellow) {
        Integer[] answer = {};
        for (int i = 1; i <= yellow; i++) {
            if(yellow % i != 0) {
                continue;
            }
            if (2 * (yellow / i + i) + 4 == brown) {
                answer = new Integer[]{yellow / i + 2, i + 2};
                break;
            }
        }
        Arrays.sort(answer, Collections.reverseOrder());
        return Arrays.stream(answer)
                .mapToInt(i -> i)
                .toArray();
    }
}
