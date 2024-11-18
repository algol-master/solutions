// 문제 : baekjoon 1431 - 시리얼 번호 (실버 3)
// 풀이 날짜 : 24/11/15
// 사용 알고리즘 : sort
// 시간 및 메모리 제한 : 2초, 128 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 104 ms
// 소요한 메모리 크기 : 9852 KB
// 풀이 소요 시간 : 약 40분
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require("fs")
// const lines = fs.readFileSync("input.txt").toString().trim().split("\n")
const lines = fs.readFileSync("/dev/stdin").toString().trim().split("\n")
const N = Number(lines[0])
const inputs = lines.slice(1)

// 구현부
inputs.sort((a, b) => {
    if (a.length === b.length) {
        const regExp = /[0-9]/
        let aSum = 0
        let bSum = 0
        for (let aIdx in a) {
            if (regExp.test(a[aIdx])) aSum += Number(a[aIdx])
        }
        for (let bIdx in b) {
            if (regExp.test(b[bIdx])) bSum += Number(b[bIdx])
        }
        
        if (aSum === bSum) {
            for (let i=0; i<a.length; i++) {
                if (a[i] !== b[i]) {
                    return a[i].charCodeAt() - b[i].charCodeAt();
                }
            }
        }
        return aSum - bSum
    }
    return a.length - b.length
})

for (let i=0; i<inputs.length; i++) {
    console.log(inputs[i])
}
