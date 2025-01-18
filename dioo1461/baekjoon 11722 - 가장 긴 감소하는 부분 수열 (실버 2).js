// 문제 : baekjoon 11722 - 가장 긴 감소하는 부분 수열 (실버 2)
// 풀이 날짜 : 25/01/02
// 사용 알고리즘 : dp
// 시간 및 메모리 제한 : 1초, 256 MB
// 시도 횟수 : 2
// 성공 여부 : 성공
// 채점 소요 시간 : 160 ms
// 소요한 메모리 크기 : 9652 KB
// 풀이 소요 시간 : 21분 13초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(lines[0])
const inputs = lines[1].split(' ').map(Number)

// 구현부
const dp = new Array(N + 1).fill(0)

dp[0] = 1

for (let i = 0; i < N; i++) {
    let max = 1
    for (let j = 0; j < i; j++) {
        if (inputs[j] <= inputs[i]) continue
        if (max < dp[j] + 1) {
            // console.log(`${inputs[j]} is bigger than ${inputs[i]}`)
            max = dp[j] + 1
        }
    }
    dp[i] = max
    // console.log(`dp[${i}] = ${dp[i]}`)
}

console.log(Math.max(...dp))
