// 문제 : baekjoon 10972 - 다음 순열 (실버 3)
// 풀이 날짜 : 24/12/24
// 사용 알고리즘 : -
// 시간 및 메모리 제한 : 1초, 256 MB
// 시도 횟수 : 8
// 성공 여부 : 성공
// 채점 소요 시간 : 108 ms
// 소요한 메모리 크기 : 11612 KB
// 풀이 소요 시간 : -
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(lines[0])
const inputs = lines[1].split(' ').map(Number)

// 구현부
var pivot = -1
for (let i=N-2; i>=0; i--) {
    if (inputs[i] < inputs[i+1]) {
        pivot = i
        break
    }
}
if (pivot == -1) {
    console.log(-1)
    return
}


var minIdx = pivot+1
for (let i= N-1; i>pivot; i--) {
    if (inputs[i] > inputs[pivot]) {
        minIdx = i
        break
    }
}
[inputs[pivot], inputs[minIdx]] = [inputs[minIdx], inputs[pivot]]

var frontPart = inputs.slice(0, pivot+1)
var backPart = inputs.slice(pivot+1, N)
backPart.sort((a,b) => a-b)

var ansArr = [...frontPart, ...backPart]
console.log(ansArr.join(' '))