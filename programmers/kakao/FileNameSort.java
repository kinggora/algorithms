package kakao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileNameSort {

    public static void main(String[] args) {
        FileNameSort f = new FileNameSort();
        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] sortedFiles1 = f.solution(files1);
        for(String file: sortedFiles1) {
            System.out.print(file + ", ");
        }
        System.out.println();

        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        String[] sortedFiles2 = f.solution(files2);
        for(String file: sortedFiles2) {
            System.out.print(file + " ");
        }

    }

    // 파일명: 영문 대소문자, 숫자, 공백(" "), 마침표("."), 빼기 부호("-")
    // - HEAD: 숫자가 아닌 문자. 최소 1글자 이상
    // - NUMBER: 1~5글자 사이의 연속된 숫자. 0으로 시작 가능
    // - TAIL: HEAD와 NUMBER 뒤의 나머지 부분. 문자/숫자/빈 문자열
    public String[] solution(String[] files) {

        // FileName 리스트 생성
        List<FileName> fileNameList = new ArrayList<>();
        for(int index = 0; index < files.length; index++) {
            String file = files[index];
            int i = 0; // NUMBER 부분이 시작되는 인덱스
            while(!isNumber(file.charAt(i))) {
                i++;
            }
            int j = i + 1; // NUMBER 다음 부분 중 첫 번째 인덱스
            while(j < i + 5 && j < file.length()) {
                if(!isNumber(file.charAt(j))) {
                    break;
                }
                j++;
            }
            FileName fileName = new FileName(
                    file.substring(0, i),
                    Integer.parseInt(file.substring(i, j)),
                    index
            );
            fileNameList.add(fileName);
        }

        // FileNameList 정렬
        // 1. HEAD 기준 사전식 정렬 (대소문자 구분 X)
        // 2. NUMBER 기준 숫자 오름차순 정렬 (앞자리 0은 무시)
        // 3. 입력순(files[] index 순)
        fileNameList.sort(new Comparator<FileName>() {
            @Override
            public int compare(FileName o1, FileName o2) {
                // 대소문자 구분 없이 정렬하기 위해 소문자로 변환
                String head1 = o1.head.toLowerCase();
                String head2 = o2.head.toLowerCase();
                // 1. head: 사전식
                if(head1.equals(head2)) {
                    // 2. number: 오름차순
                    if(o1.number == o2.number) {
                        // 3. index: 오름차순
                        return o1.index - o2.index;
                    }
                    return o1.number - o2.number;
                }
                return head1.compareTo(head2);
            }
        });

        // List<FileName> -> String[] 변환
        return fileNameList.stream()
                .map(fileName -> files[fileName.index])
                .toArray(String[]::new);
    }

    private boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    static class FileName {
        public final String head;
        public final int number;
        public final int index;

        public FileName(String head, int number, int index) {
            this.head = head;
            this.number = number;
            this.index = index;
        }
    }
}
