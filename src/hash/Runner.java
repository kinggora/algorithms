package hash;


import java.util.HashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        Runner r = new Runner();
        String[] p1 = {"leo", "kiki", "eden"};
        String[] c1 = {"eden", "kiki"};
        System.out.println(r.solution(p1, c1));

        String[] p2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] c2 = {"josipa", "filipa", "marina", "nikola"};
        System.out.println(r.solution(p2, c2));

        String[] p3 = {"mislav", "stanko", "mislav", "ana"};
        String[] c3 = {"stanko", "ana", "mislav"};
        System.out.println(r.solution(p3, c3));

    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> completeMap = new HashMap<>();
        // key: 완주한 사람 이름, value: 같은 이름을 가진 사람 수
        for (String cName : completion) {
            if (completeMap.containsKey(cName)) {
                completeMap.put(cName, completeMap.get(cName) + 1);
            } else {
                completeMap.put(cName, 1);
            }
        }

        // map에 참가자 이름이 존재한다면 value--
        // map에 참가자 이름이 존재하지 않거나, value가 1 미만이면 이름 반환
        for (String pName : participant) {
            if(completeMap.containsKey(pName) && completeMap.get(pName) > 0) {
                completeMap.put(pName, completeMap.get(pName) - 1);
            } else {
                answer = pName;
                break;
            }
        }
        return answer;
    }
}

