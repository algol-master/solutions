// 문제 : baekjoon 19583 - 싸이버개강총회 (실버 2)
// 문제 링크 : 
// 풀이 날짜 : 24/10/15
// 사용 알고리즘 : hashmap, brute force
// 시간 및 메모리 제한 : 1초, 1024 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 432 ms
// 소요한 메모리 크기 : 80380 KB
// 풀이 소요 시간 : 23분 09초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require("fs");
// const lines = fs.readFileSync("input.txt").toString().trim().split("\n");
const lines = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
var [S, E, Q] = lines[0].split(" ").map((v) => {
  let [h, m] = v.split(":").map(Number);
  return h * 60 + m;
});

var inputs = lines.slice(1).map((v) => v.trim().split(" "));
inputs = inputs.map((v) => {
  let [h, m] = v[0].split(":").map(Number);
  return [h * 60 + m, v[1]];
});

// 구현부
var set = new Set();
var cnt = 0;

inputs.forEach((v, i) => {
  if (v[0] <= S) {
    set.add(v[1]);
    return;
  }

  if (E <= v[0] && v[0] <= Q) {
    if (set.has(v[1])) {
      set.delete(v[1]);
      cnt++;
    }
    return;
  }
});

console.log(cnt);
