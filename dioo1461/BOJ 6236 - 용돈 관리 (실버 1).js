// 문제 : BOJ 6236 - 용돈 관리 (실버 1)
// 풀이 날짜 : 25/01/25
// 사용 알고리즘 : binary search
// 시간 및 메모리 제한 : 1초, 128 MB
// 시도 횟수 : 2
// 성공 여부 : 성공
// 채점 소요 시간 : 220 ms
// 소요한 메모리 크기 : 25984 KB
// 풀이 소요 시간 : 25분 28초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync(0, 'utf-8').toString().trim().split('\n')
const [N, M] = lines[0].split(' ').map(Number)
const inputs = lines.slice(1).map(Number)

// 구현부
const simulate = (withdraw) => {
    let change = 0
    let count = 0
    for (let cost of inputs) {
        if (change < cost) {
            count++
            change = withdraw
        }
        if (change < cost) return false
        if (count > M) return false
        change -= cost
    }
    return true
}

let left = 0
let right = inputs.reduce((acc, cur) => acc + cur, 0)
while (left < right) {
    let mid = Math.floor((left + right) / 2)
    if (!simulate(mid)) {
        left = mid + 1
    } else {
        right = mid
    }
}
console.log(left)
