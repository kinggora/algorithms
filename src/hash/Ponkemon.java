package hash;

import java.util.HashSet;

// 폰켓몬
public class Ponkemon {
    public static void main(String[] args) {
        Ponkemon p = new Ponkemon();
        int[] ex1 = {3, 1, 2, 3};
        int[] ex2 = {3, 3, 3, 2, 2, 4};
        int[] ex3 = {3, 3, 3, 2, 2, 2};

        System.out.println(p.solution(ex1));
        System.out.println(p.solution(ex2));
        System.out.println(p.solution(ex3));
    }

    public int solution(int[] nums) {
        int answer = nums.length / 2; // 선택 가능한 종류의 최댓값
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // 실제 종류 수와 비교
        if(set.size() < answer) {
            answer = set.size();
        }
        return answer;
    }

}
