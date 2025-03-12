package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj3273 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine()); // 수열의 크기
        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int x = Integer.parseInt(reader.readLine());
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        int start = 0;
        int end = n - 1;
        Arrays.sort(num); // 오름차순 정렬 (오른쪽으로 갈수록 수가 커짐)
        while(start < end) {
            int sum = num[start] + num[end];
            if(sum == x) {
                cnt++;
                // 합이 x를 만족하는 다음 쌍을 찾기 위해 start, end 포인터 모두 이동
                start++;
                end--;
            } else if(sum < x) { // sum이 커져야 하므로 start 포인터를 오른쪽으로 이동)
                start++;
            } else { // sum이 작아져야 하므로 end 포인터를 왼쪽으로 이동
                end--;
            }
        }
        System.out.println(cnt);
    }
}
