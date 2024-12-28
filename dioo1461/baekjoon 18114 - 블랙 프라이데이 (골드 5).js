// 문제 : baekjoon 18114 - 블랙 프라이데이 (골드 5)
// 풀이 날짜 : 24/11/19
// 사용 알고리즘 : sort, two pointer
// 시간 및 메모리 제한 : 1초, 1024 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 220 ms
// 소요한 메모리 크기 : 10072 KB
// 풀이 소요 시간 : 약 10분
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require("fs")
// const lines = fs.readFileSync("input.txt").toString().trim().split("\n")
const lines = fs.readFileSync("/dev/stdin").toString().trim().split("\n")
const [N, C] = lines[0].split(' ').map(Number)
const inputs = lines[1].split(' ').map(Number)

// 구현부

inputs.sort((a, b) => a - b)
for (let i=0; i<N-2; i++) {
    var l = i+1
    var r = N-1
    var targetNum = inputs[i]
    while (l<r) {
        if (targetNum == C || inputs[l] == C || inputs[r] == C) {
            console.log(1)
            return
        }

        if (inputs[l] + targetNum == C || inputs[r] + targetNum == C || inputs[l] + inputs[r] == C) {
            console.log(1)
            return
        }

        const sum = targetNum + inputs[l] + inputs[r]
        if (sum < C) {
            l++
        } else if (sum > C) {
            r--
        } else {
            console.log(1)
            return
        }
    }
}

console.log(0)
