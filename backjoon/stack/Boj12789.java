package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        // 현재 줄 서있는 곳
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // 한 명씩만 설 수 있는 공간
        Stack<Integer> stack = new Stack<>();

        // 찾는 번호표: i번
        for(int i = 1; i <= n; i++) {
            // stack 에서 i 찾기
            if(!stack.isEmpty() && stack.peek() == i) {
                stack.pop();
                continue; // 찾으면 다음 번호표로
            }
            // token 에서 i 찾기
            boolean result = false;
            while(tokenizer.hasMoreTokens()) {
                int number = Integer.parseInt(tokenizer.nextToken());
                if(number == i) {
                    result = true;
                    break;
                }
                stack.push(number); // i가 아닌 수 -> stack에 저장
            }
            // stack 과 token 에서 i를 찾지 못한 경우 실패
            if(!result) {
                System.out.println("Sad");
                return;
            }
        }
        System.out.println("Nice");
    }
}
