package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        String burst = reader.readLine();

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < string.length(); i++) {
            stack.push(string.charAt(i));
            if(stack.size() >= burst.length()) {
                boolean burstOut = true;
                for(int j = 0; j < burst.length(); j++) {
                    if (stack.get(stack.size() - burst.length() + j) != burst.charAt(j)) {
                        burstOut = false;
                        break;
                    }
                }
                if(burstOut) {
                    for(int j = 0; j < burst.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        if(stack.size() == 0) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for(char c: stack) {
                sb.append(c);
            }
            System.out.println(sb);
        }
    }
}
