// 문제 : BOJ 20040 - 사이클 게임 (골드4)
// 풀이 날짜 : 25/01/19
// 사용 알고리즘 : union find
// 시간 및 메모리 제한 : 1초, 512 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 2576 ms
// 소요한 메모리 크기 : 368244 KB
// 풀이 소요 시간 : 17분 25초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync(0, 'utf-8').toString().trim().split('\n')
const [N, M] = lines[0].split(' ').map(Number)
const inputs = lines.slice(1).map((v) => v.split(' ').map(Number))

// 구현부
const parent = new Array(N).fill(-1)
const height = new Array(N).fill(0)

const find = (x) => {
    if (parent[x] < 0) return x
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

    if (height[higher] === height[lower]) height[higher]++
    height[lower] = 0
    parent[higher] += parent[lower]
    parent[lower] = higher
    return true
}

for (let [i, v] of Object.entries(inputs)) {
    if (!union(v[0], v[1])) {
        console.log(Number(i) + 1)
        return
    }
}
console.log(0)
