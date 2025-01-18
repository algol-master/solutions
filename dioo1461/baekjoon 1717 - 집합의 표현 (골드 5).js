// 문제 : baekjoon 1717 - 집합의 표현 (골드 5)
// 풀이 날짜 : 25/01/19
// 사용 알고리즘 : union find
// 시간 및 메모리 제한 : 2초, 128 MB
// 시도 횟수 : 5
// 성공 여부 : 성공
// 채점 소요 시간 : 388 ms
// 소요한 메모리 크기 : 68568 KB
// 풀이 소요 시간 : 24분 44초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync(0, 'utf-8').toString().trim().split('\n')
const [N, M] = lines[0].split(' ').map(Number)
const inputs = lines.slice(1).map((v) => v.split(' ').map(Number))

// 구현부
const parent = new Array(N + 1).fill(-1)
const height = new Array(N + 1).fill(0)

const find = (x) => {
    if (parent[x] < 0) {
        return x
    }

    return (parent[x] = find(parent[x]))
}

const union = (a, b) => {
    let higher, lower
    let pa = find(a)
    let pb = find(b)
    if (pa === pb) return false

    if (height[pa] >= height[pb]) {
        higher = pa
        lower = pb
    } else {
        higher = pb
        lower = pa
    }

    parent[higher] += parent[lower]
    parent[lower] = higher

    if (height[higher] === height[lower]) {
        height[higher]++
    }
    height[lower] = 0
    return true
}

let count = 0
let buffer = ''
inputs.forEach((v) => {
    if (count > 10000) {
        console.log(buffer)
        count = 0
        buffer = ''
    }
    const [op, a, b] = v
    if (op === 0) {
        union(a, b)
    } else if (op === 1) {
        if (find(a) !== find(b)) {
            buffer += 'NO\n'
        } else {
            buffer += 'YES\n'
        }
    }
})

console.log(buffer)
