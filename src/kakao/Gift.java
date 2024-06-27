package kakao;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Gift {

    public static void main(String[] args) {
        Gift gift = new Gift();
        String[] friends1 = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts1 = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi",
                "frodo muzi", "frodo ryan", "neo muzi"};
        System.out.println(gift.solution(friends1, gifts1));

        String[] friends2 = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts2 = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro",
                "alessandro david"};
        System.out.println(gift.solution(friends2, gifts2));

        String[] friends3 = {"a", "b", "c"};
        String[] gifts3 = {"a b", "b a", "c a", "a c", "a c", "c a"};
        System.out.println(gift.solution(friends3, gifts3));

    }

    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giftRecord = new HashMap<>();
        Map<String, Integer> giftPoint = new HashMap<>();
        Map<String, Integer> nextMonthGifts = new HashMap<>();

        for(String friend : friends) {
            giftRecord.put(friend, new HashMap<>());
            giftPoint.put(friend, 0);
            nextMonthGifts.put(friend, 0);
        }

        for (String gift : gifts) {
            String[] fromTo = gift.split(" ");
            Map<String, Integer> giftMap = giftRecord.get(fromTo[0]);
            giftMap.put(fromTo[1], giftMap.getOrDefault(fromTo[1], 0) + 1);

            giftPoint.put(fromTo[0], giftPoint.get(fromTo[0]) + 1);
            giftPoint.put(fromTo[1], giftPoint.get(fromTo[1]) - 1);
        }

        // 선물을 주고받은 기록이 있다면, 더 많은 선물을 준 사람 + 1
        // 선물을 주고 받은 기록이 없거나 수가 같다면, 선물 지수가 더 큰 사람 + 1
        for(String a : friends) {
            for (String b : friends) {
                if (!a.equals(b)) {
                    int fromA = giftRecord.get(a).getOrDefault(b, 0);
                    int fromB = giftRecord.get(b).getOrDefault(a, 0);
                    if(fromA > fromB || (fromA == fromB && giftPoint.get(a) > giftPoint.get(b))) {
                        nextMonthGifts.put(a, nextMonthGifts.get(a) + 1);
                    }
                }
            }
        }
        return Collections.max(nextMonthGifts.values());
    }
}