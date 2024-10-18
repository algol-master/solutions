// 문제 : programmers 42579 - 베스트앨범
// 풀이 날짜 : 24/10/15
// 사용 알고리즘 : hashmap, implementation
// 시간 및 메모리 제한 : -초, -MB
// 시도 횟수 : 2
// 성공 여부 : 성공
// 풀이 소요 시간 : 28분 26초
// 풀이 참고 사이트 : -

function solution(genres, plays) {
  var answer = [];
  const len = genres.length
  
  // [genre, [[[index, plays[index]], ...],  totalCount]]
  const compareGenre = (a, b) => {
      return b[1][1] - a[1][1]
  }
  
  // [index, plays[index]]
  const comparePlays = (a, b) => {
      return b[1] - a[1]
  }
  
  var map = new Map()
  
  var genreRanking = []
  
  // map : genre -> [[[index, plays[index]], ...],  totalCount]
  for (let i=0; i<len; i++) {
      if (!map.get(genres[i])) {
          map.set(genres[i], [[], 0])
      }
      var element = map.get(genres[i])
      element[0].push([i, plays[i]])
      element[1] += plays[i]
  }
  
  genreRanking = [...map]
  
  genreRanking.sort(compareGenre)
  
  // v = [genre, [[index, plays[index]], ...],  totalCount]]
  genreRanking.forEach((v, i) => {
      // songs = [[index, plays[index]], ...]
      var songs = [...v[1][0]]
      
      songs.sort(comparePlays)
      answer.push(songs[0][0], songs[1][0])
  })

  
  return answer;
}
