// 문제 : baekjoon 1260 - DFS와 BFS (실버 2)
// 풀이 날짜 : 25/01/19
// 사용 알고리즘 : dfs, bfs
// 시간 및 메모리 제한 : 2초, 128 MB
// 시도 횟수 : 2
// 성공 여부 : 성공
// 채점 소요 시간 : 184 ms
// 소요한 메모리 크기 : 16540 KB
// 풀이 소요 시간 : 21분 19초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const [N, M, V] = lines[0].split(' ').map(Number)
const edges = lines.slice(1).map((v) => v.split(' ').map(Number))

// 구현부
const graph = new Map()
for (let i = 0; i <= N; i++) {
    graph.set(i, [])
}
edges.forEach((edge) => {
    graph.get(edge[0]).push(edge[1])
    graph.get(edge[1]).push(edge[0])
})
for (let i = 0; i <= N; i++) {
    graph.get(i).sort((a, b) => a - b)
}

let visited = new Array(N + 1).fill(false)

let dfsAns = ''
const dfs = (vertex) => {
    visited[vertex] = true
    dfsAns += ` ${vertex}`

    graph.get(vertex).forEach((dest) => {
        if (!visited[dest]) {
            dfs(dest)
        }
    })
}
dfs(V)

visited = new Array(N + 1).fill(false)
let bfsAns = ''

const queue = [V]
visited[V] = true

while (queue.length > 0) {
    const cur = queue.shift()
    bfsAns += `${cur} `

    graph.get(cur).forEach((dest) => {
        if (!visited[dest]) {
            visited[dest] = true
            queue.push(dest)
        }
    })
}

console.log(dfsAns.trim())
console.log(bfsAns.trim())