// 문제 : baekjoon 11726 - 2xn 타일링 (실버 3)
// 풀이 날짜 : 25/01/02
// 사용 알고리즘 : dp
// 시간 및 메모리 제한 : 1초, 256 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 152 ms
// 소요한 메모리 크기 : 9644 KB
// 풀이 소요 시간 : 17분 12초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(lines[0])

// 구현부
const dp = new Array(N + 1).fill(0)
dp[1] = 1
dp[2] = 2
dp[3] = 3
for (let i = 3; i <= N; i++) {
    dp[i] = (dp[i - 2] + dp[i - 1]) % 10007
}

console.log(dp[N])
