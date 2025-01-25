// 문제 : baekjoon 2606 - 바이러스
// 풀이 날짜 : 25/01/15
// 사용 알고리즘 : Union find
// 시간 및 메모리 제한 : 1초, 128 MB
// 시도 횟수 : 2
// 성공 여부 : 성공
// 채점 소요 시간 : 96 ms
// 소요한 메모리 크기 : 9460 KB
// 풀이 소요 시간 : 약 20분
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(lines[0])
const K = Number(lines[1])
const inputs = lines.slice(2).map((v) => v.split(' ').map(Number))

// 구현부
const graph = new Array(N + 1).fill(0).map((v) => new Array(N + 1).fill(false))
inputs.forEach((v) => {
    const start = v[0]
    const end = v[1]
    graph[start][end] = true
    graph[end][start] = true
})

const parent = new Array(N + 1).fill(-1)
const height = new Array(N + 1).fill(0)

const find = (vertex) => {
    if (parent[vertex] < 0) return vertex
    return (parent[vertex] = find(parent[vertex]))
}

const union = (a, b) => {
    a = find(a)
    b = find(b)

    if (a === b) return false

    let higher, lower
    if (height[a] >= height[b]) {
        higher = a
        lower = b
    } else {
        higher = b
        lower = a
    }

    parent[higher] += parent[lower]
    parent[lower] = higher
    if (height[higher] === height[lower]) {
        height[higher]++
    }

    height[lower] = 0
    return true
}
inputs.forEach((v) => {
    union(v[0], v[1])
})

let ans = parent[find(1)]
console.log(-ans - 1)
