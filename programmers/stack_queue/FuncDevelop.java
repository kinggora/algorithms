package stack_queue;

import java.util.*;

public class FuncDevelop {

    public static void main(String[] args) {
        FuncDevelop f = new FuncDevelop();
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        System.out.println(Arrays.toString(f.solution(progresses1, speeds1)));
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(f.solution(progresses2, speeds2)));
    }

    // progresses: 작업의 진도 (앞설수록 배포 우선순위 있음)
    // speeds: 개발 속도
    //
    // progress + n * speed >= 100 이 되는 n일 후에 배포 가능
    // 앞선 기능이 완성되는 날에 먼저 완성된 뒤 기능 함께 배포
    // 배포는 하루에 1번만 가능
    // 배포되는 날마다 하루에 몇 개의 기능이 배포되는지 출력
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int days = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            if(!queue.isEmpty() && queue.peek() < days) {
                answer.add(queue.size());
                queue.clear();
            }
            queue.add(days);
        }
        answer.add(queue.size());
        return answer.stream()
                .mapToInt(i->i).toArray();
    }
}
