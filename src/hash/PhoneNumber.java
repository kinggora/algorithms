package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PhoneNumber {

    public static void main(String[] args) {
        PhoneNumber p = new PhoneNumber();
        String[] ex1 = {"119", "97674223", "1195524421"};
        String[] ex2 = {"123","456","789"};
        String[] ex3 = {"12","123","1235","567","88"};
        System.out.println(p.solution(ex1));
        System.out.println(p.solution(ex2));
        System.out.println(p.solution(ex3));
    }

    // 접두어면 false, 접두어가 아니면 true 반환
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Set<String> set = new HashSet<>(Arrays.asList(phone_book));
        for (String phoneNum : phone_book) {
            for (int j = 1; j < phoneNum.length(); j++) {
                // 전화번호 입력은 중복되지 않으므로 endIndex를 length-1까지 증가시키며 set 내 존재여부 확인
                if (set.contains(phoneNum.substring(0, j))) {
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }
}
