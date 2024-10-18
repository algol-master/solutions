// 문제 : programmers 42577 - 전화번호 목록
// 풀이 날짜 : 24/10/15
// 사용 알고리즘 : hashmap, brute force
// 시간 및 메모리 제한 : -초, -MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 풀이 소요 시간 : 21분 59초
// 풀이 참고 사이트 : -

function solution(phone_book) {
  var set = new Set();

  const compare = (a, b) => {
    return a.length - b.length;
  };

  phone_book.sort(compare);

  for (idx in phone_book) {
    var str = "";
    for (let i = 0; i < phone_book[idx].length; i++) {
      str += phone_book[idx][i];
      if (set.has(str)) {
        return false;
      }
    }
    set.add(phone_book[idx]);
  }

  return true;
}
