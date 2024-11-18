// 문제 : baekjoon 3151 - 합이 0 (골드 4)
// 풀이 날짜 : 24/11/19
// 사용 알고리즘 : sort, two poiter
// 시간 및 메모리 제한 : 4초, 128 MB
// 시도 횟수 : 4
// 성공 여부 : 성공
// 채점 소요 시간 : 424 ms
// 소요한 메모리 크기 : 10716 KB
// 풀이 소요 시간 : 1시간 11분 19초
// 풀이 참고 사이트 : https://velog.io/@zigje9/%EB%B0%B1%EC%A4%80-3151-%ED%95%A9%EC%9D%B4-0

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(lines[0])
const inputs = lines[1].split(' ').map(Number)

// 구현부
inputs.sort((a, b) => a - b)
var ans = 0

for (let i = 0; i < N - 2; i++) {
    let l = i + 1
    let r = N - 1
    let targetNum = -inputs[i]
    while (l < r) {
        if (inputs[l] + inputs[r] < targetNum) {
            l++
        } else if (inputs[l] + inputs[r] > targetNum) {
            r--
        } else {
            const lVal = inputs[l]
            const rVal = inputs[r]
            var lCnt = 0
            var rCnt = 0
            if (lVal == rVal) {
                while (l < N && inputs[l] == lVal) {
                    l++
                    lCnt++
                }
                ans += (lCnt * (lCnt - 1)) / 2
                break
            }
            while (inputs[l] == lVal) {
                l++
                lCnt++
            }
            while (inputs[r] == rVal) {
                r--
                rCnt++
            }
            ans += lCnt * rCnt
        }
    }
}

console.log(ans)
