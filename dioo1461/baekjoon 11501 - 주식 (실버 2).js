// 문제 : baekjoon 11501 - 주식 (실버 2)
// 풀이 날짜 : 24/12/27
// 사용 알고리즘 : greedy
// 시간 및 메모리 제한 : 5초, 256 MB
// 시도 횟수 : 5
// 성공 여부 : 성공
// 채점 소요 시간 : 2892 ms
// 소요한 메모리 크기 : 471164 KB
// 풀이 소요 시간 : 50분 24초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const T = Number(lines[0])

// 구현부
var tc = 0
while (tc < T) {
    const days = lines[1 + tc*2]
    const costs = lines[2 + tc*2].split(' ').map(Number)
    var money = BigInt(0)

    var startIdx = 0
    while (startIdx < days) {
        var maxIdx = startIdx
        for (let i=startIdx; i<days; i++) {
            if (costs[maxIdx] <= costs[i]) {
                maxIdx = i
            }
        }
        for (let i = startIdx; i<maxIdx; i++) {
            money -= BigInt(costs[i])
        }
        money += BigInt(costs[maxIdx]) * BigInt(maxIdx - startIdx)
        startIdx = maxIdx + 1
    }
    console.log(money.toString())
    tc++
}
