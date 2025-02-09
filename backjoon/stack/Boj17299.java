package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj17299 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] seq = new int[N];
        int[] f = new int[1000001];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            f[seq[i]]++;
        }

        Stack<Integer> stack = new Stack<>();
        int[] ngf = new int[N];
        for(int i = N - 1; i >= 0; i--) {
            while(!stack.isEmpty()) {
                if(f[seq[i]] < f[stack.peek()]) {
                    ngf[i] = stack.peek();
                    stack.push(seq[i]);
                    break;
                } else {
                    stack.pop();
                }
            }
            if(stack.isEmpty()) {
                ngf[i] = -1;
                stack.push(seq[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int n : ngf) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}
