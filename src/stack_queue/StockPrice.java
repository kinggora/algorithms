package stack_queue;

import java.util.Arrays;
import java.util.Stack;

public class StockPrice {

    public static void main(String[] args) {
        StockPrice s = new StockPrice();
        int[] prices = {1, 2, 3, 2, 3};
        System.out.println(Arrays.toString(s.solution(prices)));
    }


    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[prices.length];
        // 스택 내부는 오름차 순으로 유지
        for (int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int pop = stack.pop();
                arr[pop] = i - pop;
            }
            stack.add(i);
        }
        // 스택에 남은 주식은 끝까지 가격을 유지
        // 마지막 인덱스 - 자신의 인덱스
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            arr[pop] = (prices.length - 1) - pop;
        }
        return arr;
    }
}
