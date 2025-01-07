package hash;

import java.util.*;

public class Clothes {
    public static void main(String[] args) {
        Clothes c = new Clothes();
        String[][] clothes1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(c.solution(clothes1));

        String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        System.out.println(c.solution(clothes2));
    }

    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            String clothType = clothes[i][1];
            map.put(clothType, map.getOrDefault(clothType, 0) + 1);
        }

        int answer = 1;
        // count(각 종류에서 하나를 고르는 경우) + 1(고르지 않는 경우)
        for(int count : map.values()) {
            answer *= (count + 1);
        }
        // 한 개 이상은 반드시 착용해야 하기 때문에, 모든 종류에서 하나도 고르지 않는 경우의 수 제거
        answer--;
        return answer;
    }
}
