package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj1725 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        stack.push(0);
        for(int i = 1; i < n; i++) {
            while(!stack.isEmpty()) {
                if(arr[stack.peek()] <= arr[i]) {
                    break;
                }
                int height = arr[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int height = arr[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        System.out.println(maxArea);
    }
}
