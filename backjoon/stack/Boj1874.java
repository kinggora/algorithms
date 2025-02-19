package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj1874 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] sequence = new int[n];
        for(int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(reader.readLine());
        }

        CustomStack stack = new CustomStack();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int value = 1;
        while(index < n) {
            if(sequence[index] != stack.top()) {
                if(value > n) {
                    System.out.println("NO");
                    return;
                } else {
                    stack.push(value);
                    value++;
                    sb.append("+").append("\n");
                }
            } else {
                stack.pop();
                index++;
                sb.append("-").append("\n");
            }
        }
        System.out.print(sb);
    }

    static class CustomStack {
        List<Integer> stack = new ArrayList<>();

        public void push(int x) {
            stack.add(x);
        }

        public int pop() {
            if(stack.isEmpty()) {
                return -1;
            }
            return stack.remove(stack.size() - 1);
        }

        public int top() {
            if(stack.isEmpty()) {
                return -1;
            }
            return stack.get(stack.size() - 1);
        }
    }
}
