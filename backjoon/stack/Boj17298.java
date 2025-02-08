package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj17298 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] seq = new int[N];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[N];
        for(int i = N - 1; i >= 0; i--) {
            while(!stack.isEmpty()) {
                if(seq[i] < stack.peek()) {
                    nge[i] = stack.peek();
                    stack.push(seq[i]);
                    break;
                } else {
                    stack.pop();
                }
            }
            if(stack.isEmpty()) {
                nge[i] = -1;
                stack.push(seq[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int n : nge) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}
