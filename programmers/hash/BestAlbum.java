package hash;

import java.util.*;

public class BestAlbum {
    public static void main(String[] args) {
        BestAlbum b = new BestAlbum();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] solution = b.solution(genres, plays);
        for(int index: solution) {
            System.out.println(index);
        }
    }

    // 정렬 조건
    // 1. 장르별 재생 횟수 내림차순
    // 2. 곡별 재생 횟수 내림차순 (재생 횟수가 같을 경우, 고유번호 오름차순)
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 곡 리스트 저장
        Map<String, List<Song>> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            if(!map.containsKey(genre)) {
                map.put(genre, new ArrayList<>());
            }
            map.get(genre).add(new Song(i, plays[i]));
        }

        // 장르 리스트를 장르별 재생 횟수에 대해 내림차순 정렬
        List<String> sortedGenres = new ArrayList<>(map.keySet());
        sortedGenres.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int sum1 = map.get(o1).stream()
                        .mapToInt(Song::getPlayCount).sum();
                int sum2 = map.get(o2).stream()
                        .mapToInt(Song::getPlayCount).sum();
                return sum2 - sum1;
            }
        });

        // 곡 리스트를 재생 횟수에 대해 내림차순 정렬하고 장르별로 2곡까지 고유번호를 정답 리스트에 추가
        List<Integer> answerList = new ArrayList<>();
        for(String genre: sortedGenres) {
            List<Song> songs = map.get(genre);
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    // 재생 횟수가 같을 경우, 고유 번호에 대해 오름차순 정렬
                    if(o1.getPlayCount() == o2.getPlayCount()) {
                        return o1.getIndex() - o2.getIndex();
                    }
                    return o2.getPlayCount() - o1.getPlayCount();
                }
            });
            answerList.add(songs.get(0).getIndex());
            if(songs.size() > 1) {
                answerList.add(songs.get(1).getIndex());
            }
        }

        // List<Integer> -> int[] 변환 후 반환
        return answerList.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    static class Song {
        private final int index;
        private final int playCount;

        public Song(int index, int playCount) {
            this.index = index;
            this.playCount = playCount;
        }

        public int getIndex() {
            return index;
        }

        public int getPlayCount() {
            return playCount;
        }
    }
}
