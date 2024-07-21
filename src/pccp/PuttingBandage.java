package pccp;

public class PuttingBandage {

    public static void main(String[] args) {
        PuttingBandage p = new PuttingBandage();
        int[] bandage1 = {5, 1, 5};
        int health1 = 30;
        int[][] attacks1 = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
        System.out.println(p.solution(bandage1, health1, attacks1)); // 5

        int[] bandage2 = {3, 2, 7};
        int health2 = 20;
        int[][] attacks2 = {{1, 15}, {5, 16}, {8, 6}};
        System.out.println(p.solution(bandage2, health2, attacks2)); // -1

        int[] bandage3 = {4, 2, 7};
        int health3 = 20;
        int[][] attacks3 = {{1, 15}, {5, 16}, {8, 6}};
        System.out.println(p.solution(bandage3, health3, attacks3)); // -1

        int[] bandage4 = {1, 1, 1};
        int health4 = 5;
        int[][] attacks4 = {{1, 2}, {3, 2}};
        System.out.println(p.solution(bandage4, health4, attacks4)); // 3
    }

    // 붕대감기: t초동안 붕대를 감으면서 1초마다 x만큼의 체력을 회복
    // t초 연속으로 붕대를 감으면 y만큼 추가로 회복
    // 최대 체력보다 체력이 커지는 것은 불가능
    // 기술을 쓰는 도중 공격을 당하면 기술이 취소
    // 기술이 취소당하거나 기술이 끝나면 즉시 붕대 감기를 다시 사용 -> 연속 성공 시간 초기화
    // 체력이 0 이하가 되면 캐릭터가 죽으며 더이상 체력 회복 X

    /**
     *
     * @param bandage { 붕대 감기 기술 시전 시간, 1초당 회복량, 추가 회복량 }
     * @param health 최대 체력
     * @param attacks {{ 몬스터의 공격 시간, 피해량 }}
     * @return 모든 공격이 끝난 직후 남은 체력 (0 이하일 경우 -1)
     */
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxRecoveryTime = bandage[0];
        int recovery = bandage[1];
        int extraRecovery = bandage[2];

        int hp = health;
        for (int i = 0; i < attacks.length; i++) {
            int[] attack = attacks[i];
            if(hp < health) {
                int beforeAttackTime = attacks[i - 1][0];
                int t = attack[0] - beforeAttackTime - 1; // 공격 받기 전 기술을 연달아 사용한 시간
                hp += t * recovery + t / maxRecoveryTime * extraRecovery; // 회복 (+ 추가 회복)
            }
            if(hp > health) { // 최대 체력 이상 회복 불가
                hp = health;
            }
            hp -= attack[1]; // 몬스터 공격
            if(hp <= 0) { // 캐릭터 죽음
                hp = -1;
                break;
            }
        }
        return hp;
    }
}
