package sleepyhoon.hash;

import java.util.*;

class PG42579 {
    static class Song {
        String genre;
        int play;
        int index;
        boolean visited = false;
        public Song(String genre,int play,int index) {
            this.genre = genre;
            this.play = play;
            this.index = index;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int length = plays.length;
        //
        ArrayList<Song> songList = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<length;i++) {
            songList.add(new Song(genres[i],plays[i],i));
            map.put(genres[i],map.getOrDefault(genres[i],0) + plays[i]);
        }
        // 노래는 플레이 시간을 기준으로 내림차순 정렬
        songList.sort((o1, o2) -> (o2.play - o1.play));
        ArrayList<Map.Entry<String,Integer>> genreList = new ArrayList<>(map.entrySet());
        // map은 순서가 없으니, ArrayList를 사용해서 정렬해보자.
        // 제일 많이 들은 장르를 알기 위해 선언했다.
        genreList.sort((o1,o2) -> o2.getValue() - o1.getValue());
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0;i<genreList.size();i++){
            int count = 0;
            for(int j=0;j<songList.size();j++){
                if(songList.get(j).visited) continue;
                if(Objects.equals(songList.get(j).genre, genreList.get(i).getKey())) {
                    answer.add(songList.get(j).index);
                    songList.get(j).visited = true;
                    count++;
                }
                if(count==2) break;
            }
        }
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}