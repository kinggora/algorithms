package stack_queue;

import java.util.Stack;

public class CorrectBracket {
    public static void main(String[] args) {
        String s1 = "()()";
        String s2 = "(())()";
        String s3 = ")()(";
        String s4 = "(()(";
        CorrectBracket c = new CorrectBracket();
        System.out.println(c.solution(s1));
        System.out.println(c.solution(s2));
        System.out.println(c.solution(s3));
        System.out.println(c.solution(s4));
    }

    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                stack.add(ch);
            } else { // ch == ')'
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
