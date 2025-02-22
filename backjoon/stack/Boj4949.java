package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj4949 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            String str = reader.readLine();
            if(str.equals(".")) {
                break;
            }
            List<String> stack = new ArrayList<>();
            boolean isValanced = true;
            for(int i = 0; i < str.length(); i++) {
                String s = String.valueOf(str.charAt(i));
                if(!"([)]".contains(s)) {
                    continue;
                }
                if("([".contains(s)) {
                    stack.add(s);
                } else if(stack.isEmpty()) {
                    isValanced = false;
                    break;
                } else {
                    String element = stack.remove(stack.size() - 1);
                    if(s.equals(")") && !element.equals("(")){
                        isValanced = false;
                        break;
                    } else if(s.equals("]") && !element.equals("[")){
                        isValanced = false;
                        break;
                    }
                }
            }
            if(isValanced && stack.isEmpty()) {
                sb.append("yes");
            } else {
                sb.append("no");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
