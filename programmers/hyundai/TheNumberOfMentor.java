package hyundai;

import java.util.*;

public class TheNumberOfMentor {
    public static void main(String[] args) {
        TheNumberOfMentor numberOfMentor = new TheNumberOfMentor();
        int k1 = 3;
        int n1 = 5;
        int[][] reqs1 = {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
        System.out.println(numberOfMentor.solution(k1, n1, reqs1)); // 25

        int k2 = 2;
        int n2 = 3;
        int[][] reqs2 = {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}};
        System.out.println(numberOfMentor.solution(k2, n2, reqs2)); // 90
    }

    private List<int[]> extraMentor;

    public int solution(int k, int n, int[][] reqs) {
        // 각 유형에 1명씩 배치하고 남는 멘토를 배정할 경우의 수(extraMentor) 구하기
        // 상담 유형 k개 중 n-k개를 중복 포함하여 뽑기 -> 중복 조합
        extraMentor = new ArrayList<>();
        int[] types = new int[k];
        for (int i = 0; i < k; i++) {
            types[i] = i + 1;
        }
        int[] result = new int[n - k]; // 유형 조합을 담을 임시 배열
        combination(0, 0, types, result);

        int min = Integer.MAX_VALUE;
        for (int[] extra : extraMentor) {
            int waitingTime = 0; // 대기 시간의 합
            Map<Integer, Queue<Integer>> scheduleMap = new HashMap<>(); // 유형별 멘토의 스케줄 시간

            // 멘토의 스케줄 시간을 우선순위 큐로 관리 -> 최소 스케줄 시간 반환
            // 상담 유형마다 1명씩 배치
            for(int i = 1; i <= k; i++) {
                scheduleMap.put(i, new PriorityQueue<>(Comparator.naturalOrder()));
                scheduleMap.get(i).add(0);
            }

            // 멘토 남는 인원 충원
            for (int type : extra) {
                scheduleMap.get(type).add(0);
            }

            // 상담 요청 스케줄링, 최소 대기 시간 계산
            for (int[] req : reqs) {
                int requestTime = req[0];
                int consultTime = req[1];
                int consultType = req[2];

                Queue<Integer> mentors = scheduleMap.get(consultType);
                int scheduledTime = mentors.remove();
                // 현재 상담 중이 아닌 멘토와 상담 시작 (대기시간 X)
                if (scheduledTime <= requestTime) {
                    mentors.add(requestTime + consultTime);
                } else {
                    // 멘토가 상담이 끝났을 때 기다리던 참가자와 상담 시작 (대기시간 O)
                    waitingTime += scheduledTime - requestTime;
                    mentors.add(scheduledTime + consultTime);
                }
                if (waitingTime > min) {
                    break;
                }
            }
            min = Math.min(min, waitingTime);
        }
        return min;
    }

    private void combination(int index, int cnt, int[] numbers, int[] result) {
        if(cnt == result.length) {
            extraMentor.add(result.clone()); // 결과 배열 clone하여 add
            return;
        }
        for (int i = index; i < numbers.length; i++) {
            result[cnt] = numbers[i];
            combination(i, cnt + 1, numbers, result);
        }
    }
}
