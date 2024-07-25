package bruteforce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SearchPrime {

    public static void main(String[] args) {
        SearchPrime s = new SearchPrime();
        String number1 = "17";
        System.out.println(s.solution(number1));
        String number2 = "011";
        System.out.println(s.solution(number2));
    }

    int max = Integer.MIN_VALUE; // 생성 가능한 숫자 중 최대 값
    Set<Integer> numberSet = new HashSet<>();
    char[] digits;
    boolean[] visited;

    public int solution(String numbers) {
        digits = numbers.toCharArray();
        // 숫자 카드로 가능한 모든 숫자 생성
        // 1 ~ numbers.length 길이의 숫자를 순차적으로 생성
        for(int i = 1; i <= numbers.length(); i++) {
            visited = new boolean[numbers.length()];
            generateNumbers("", 0, i);
        }
        // 에라토스테네스의 체: 0 ~ max 범위에서 소수 찾기
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= Math.sqrt(max); i++) {
            for (int j = 2 * i; j <= max; j += i) {
                if(isPrime[j]) {
                    isPrime[j] = false;
                }
            }
        }
        int primeCnt = 0;
        for(int num : numberSet) {
            if (isPrime[num]) {
                primeCnt++;
            }
        }
        return primeCnt;
    }

    /**
     * @param str 숫자 문자열
     * @param depth 현재 str의 길이
     * @param n 최종 str 길이
     */
    public void generateNumbers(String str, int depth, int n) {
        if(depth == n) {
            int num = Integer.parseInt(str);
            numberSet.add(num);
            max = Math.max(max, num);
            return;
        }
        for (int i = 0; i < digits.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                generateNumbers(str + digits[i], depth + 1, n);
                visited[i] = false;
            }
        }
    }
}
