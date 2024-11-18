// 문제 : baekjoon 2036 - 수열의 점수 (골드 5)
// 풀이 날짜 : 24/11/18
// 사용 알고리즘 : sort, greedy
// 시간 및 메모리 제한 : 2초, 128 MB
// 시도 횟수 : 7
// 성공 여부 : 성공
// 채점 소요 시간 : 260 ms
// 소요한 메모리 크기 : 28628 KB
// 풀이 소요 시간 : 1시간 4분 13초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const inputs = lines.slice(1).map(Number)

// 구현부

// 가장 큰 값이 맨 뒤에 오도록 sort
inputs.sort((a, b) => a - b)
var ans = BigInt(0)

var stack = []
while (inputs.length > 0 && inputs[inputs.length - 1] > 1) {
    const value = inputs.pop()
    if (stack.length < 2) {
        stack.push(value)
    } else {
        ans += BigInt(stack[0]) * BigInt(stack[1])
        stack = []
        stack.push(value)
    }
}

if (stack.length == 2) {
    ans += BigInt(stack[0]) * BigInt(stack[1])
} else if (stack.length == 1) {
    ans += BigInt(stack[0])
}
stack = []

while (inputs.length > 0 && inputs[inputs.length - 1] > 0) {
    ans += BigInt(inputs.pop())
}

// 가장 작은 값이 맨 뒤에 오도록 sort
inputs.sort((a, b) => b - a)

while (inputs.length > 0 && inputs[inputs.length - 1] <= 0) {
    const value = inputs.pop()
    if (stack.length < 2) {
        stack.push(value)
    } else {
        ans += BigInt(stack[0] * stack[1])
        stack = []
        stack.push(value)
    }
}

if (stack.length == 2) {
    ans += BigInt(stack[0] * stack[1])
} else if (stack.length == 1) {
    ans += BigInt(stack[0])
}

console.log(ans.toString())
