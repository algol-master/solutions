// 문제 : programmers 12909 - 올바른 괄호
// 풀이 날짜 : 24/10/18
// 사용 알고리즘 : stack
// 시간 및 메모리 제한 : -초, -MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 풀이 소요 시간 : 7분 7초
// 풀이 참고 사이트 : -

function solution(s) {
  var top = 0;
  s = [...s]

  for (let i = 0; i < s.length; i++) {
    if (s[i] === "(") {
      top++
    } else if (s[i] === ")") {
      if (top < 1) return false
      top--
    }
  }

  if (top === 0) return true
  return false
}
