package stack_queue;

import java.util.Stack;

public class SameNumber {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            if(stack.isEmpty()) {
                stack.add(num);
            } else if (stack.peek() != num) {
                stack.add(num);
            }
        }
        int[] answer = new int[stack.size()];
        for (int i = answer.length - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }
        return answer;
    }
}
