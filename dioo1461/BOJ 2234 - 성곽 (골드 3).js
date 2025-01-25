// 문제 : BOJ 2234 - 성곽 (골드 3)
// 풀이 날짜 : 25/01/20
// 사용 알고리즘 : union find
// 시간 및 메모리 제한 : 2초, 128 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 164 ms
// 소요한 메모리 크기 : 12528 KB
// 풀이 소요 시간 : 1시간 19분 05초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync(0, 'utf-8').toString().trim().split('\n')
const [N, M] = lines[0].split(' ').map(Number)
const inputs = lines.slice(1).map((v) => v.split(' ').map(Number))

// 구현부
const parent = new Array(M * N).fill(-1)
const height = new Array(M * N).fill(0)

const find = (x) => {
    if (parent[x] < 0) return x
    return (parent[x] = find(parent[x]))
}

const union = (a, b) => {
    const pa = find(a)
    const pb = find(b)
    if (pa === pb) return false

    let higher, lower
    if (height[pa] >= height[pb]) {
        higher = pa
        lower = pb
    } else {
        higher = pb
        lower = pa
    }

    if (height[higher] === height[lower]) {
        height[higher]++
    }
    height[lower] = 0
    parent[higher] += parent[lower]
    parent[lower] = higher
    return true
}

const southWall = 0b1000
const eastWall = 0b0100
const northWall = 0b0010
const westWall = 0b0001

for (let i = 0; i < M; i++) {
    for (let j = 0; j < N; j++) {
        const src = i * N + j
        const op = inputs[i][j]
        if (!(op & southWall)) {
            union(src, src + N)
        }
        if (!(op & eastWall)) {
            union(src, src + 1)
        }
        if (!(op & northWall)) {
            union(src, src - N)
        }
        if (!(op & westWall)) {
            union(src, src - 1)
        }
    }
}

const rooms = []
for (let i = 0; i < N * M; i++) {
    if (parent[i] < 0) {
        rooms.push([i, -parent[i]])
    }
}
const roomMap = new Map()
rooms.forEach((v) => {
    roomMap.set(v[0], v[1])
})

console.log(rooms.length)
rooms.sort((a, b) => b[1] - a[1])
console.log(rooms[0][1])

const graph = new Map()
for (let i = 0; i < N * M; i++) {
    graph.set(i, new Set())
}

for (let i = 0; i < M; i++) {
    for (let j = 0; j < N; j++) {
        const src = i * N + j
        const op = inputs[i][j]
        if (op & southWall && i + 1 < M) {
            const pa = find(src)
            const pb = find(src + N)
            if (pa !== pb) {
                graph.get(pa).add(pb)
                graph.get(pb).add(pa)
            }
        }
        if (op & eastWall && j + 1 < N) {
            const pa = find(src)
            const pb = find(src + 1)
            if (pa !== pb) {
                graph.get(pa).add(pb)
                graph.get(pb).add(pa)
            }
        }
        if (op & northWall && i - 1 >= 0) {
            const pa = find(src)
            const pb = find(src - N)
            if (pa !== pb) {
                graph.get(pa).add(pb)
                graph.get(pb).add(pa)
            }
        }
        if (op & westWall && j - 1 >= 0) {
            const pa = find(src)
            const pb = find(src - 1)
            if (pa !== pb) {
                graph.get(pa).add(pb)
                graph.get(pb).add(pa)
            }
        }
    }
}

let min = 0
rooms.forEach((room) => {
    const [num, size] = room
    graph.get(num).forEach((dest) => {
        const newSize = size + roomMap.get(dest)
        if (newSize > min) min = newSize
    })
})

console.log(min)
