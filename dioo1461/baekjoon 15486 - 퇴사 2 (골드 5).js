// 문제 : baekjoon 15486 - 퇴사 2 (골드 5)
// 풀이 날짜 : 25/01/02
// 사용 알고리즘 : dp
// 시간 및 메모리 제한 : 2초, 512 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 2032 ms
// 소요한 메모리 크기 : 317284 KB
// 풀이 소요 시간 : 23분 18초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(lines[0])
const inputs = lines.slice(1).map((v) => v.split(' ').map(Number))
inputs.unshift(0)

// 구현부
const dp = new Array(N + 2).fill(0)

for (let i = 1; i <= N; i++) {
    dp[i] = Math.max(dp[i - 1], dp[i])
    const daysRequired = inputs[i][0]
    const pay = inputs[i][1]

    if (i + daysRequired > N + 1) continue
    dp[i + daysRequired] = Math.max(dp[i + daysRequired], dp[i] + pay)
}

console.log(Math.max(...dp))
