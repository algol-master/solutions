// 문제 : BOJ 2343 - 기타 레슨
// 풀이 날짜 : 25/01/25
// 사용 알고리즘 : binary search
// 시간 및 메모리 제한 : 2초, 128 MB
// 시도 횟수 : 3
// 성공 여부 : 성공
// 채점 소요 시간 : 184 ms
// 소요한 메모리 크기 : 16796 KB
// 풀이 소요 시간 : 38분 06초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync(0, 'utf-8').toString().trim().split('\n')
const [N, M] = lines[0].split(' ').map(Number)
const inputs = lines[1].split(' ').map(Number)

// 구현부
const binS = () => {
    let left = inputs[0]
    let right = inputs.reduce((acc, cur) => acc + cur, 0)
    while (left < right) {
        let mid = Math.floor((left + right) / 2)
        const result = simulate(mid)
        // console.log(`mid: ${mid}, res: ${result}`)
        if (!result) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    console.log(left)
}

const simulate = (value) => {
    let idx = 0
    let count = M
    while (count > 0) {
        count--
        let capacity = value
        while (capacity >= inputs[idx]) {
            if (idx === inputs.length - 1) return true
            capacity -= inputs[idx]
            idx++
        }
    }

    return false
}

// inputs.sort((a, b) => a - b)
binS()
