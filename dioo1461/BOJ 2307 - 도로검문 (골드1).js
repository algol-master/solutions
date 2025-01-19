// 문제 : BOJ 2307 - 도로검문 (골드1)
// 풀이 날짜 : 25/01/19
// 사용 알고리즘 : dijkstra
// 시간 및 메모리 제한 : 1초, 128 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 3352 ms
// 소요한 메모리 크기 : 47696 KB
// 풀이 소요 시간 : 1시간 48분 39초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
// const lines = fs.readFileSync(0, 'utf-8').toString().trim().split('\n')
const [N, M] = lines[0].split(' ').map(Number)
const inputs = lines.slice(1).map((v) => v.split(' ').map(Number))

// 구현부
class PQ {
    constructor(compare) {
        this.size = 0
        this.heap = [null]
        this.compare = compare
    }

    heapifyUp() {
        let index = this.size
        while (index > 1) {
            let parentIdx = Math.floor(index / 2)
            if (this.compare(this.heap[index], this.heap[parentIdx]) < 0) {
                this.swap(index, parentIdx)
                index = parentIdx
            } else {
                break
            }
        }
    }

    heapifyDown() {
        let index = 1
        while (true) {
            let leftIdx = index * 2
            let rightIdx = index * 2 + 1
            let topIdx = index
            if (
                leftIdx <= this.size &&
                this.compare(this.heap[leftIdx], this.heap[topIdx]) < 0
            ) {
                topIdx = leftIdx
            }
            if (
                rightIdx <= this.size &&
                this.compare(this.heap[rightIdx], this.heap[topIdx]) < 0
            ) {
                topIdx = rightIdx
            }

            if (topIdx === index) break
            this.swap(topIdx, index)
            index = topIdx
        }
    }

    enqueue(data) {
        this.heap.push(data)
        this.size++
        this.heapifyUp()
    }

    dequeue() {
        const res = this.heap[1]
        this.heap[1] = this.heap.pop()
        this.size--
        this.heapifyDown()
        return res
    }

    swap(a, b) {
        ;[this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]]
    }
}

const compare = (a, b) => {
    return a[1] - b[1]
}

const graph = new Array(N + 1).fill(0).map((v) => new Array(N + 1).fill(null))

inputs.forEach((v) => {
    const [a, b, cost] = v
    graph[a][b] = cost
    graph[b][a] = cost
})

const dijkstra = (start) => {
    let count = 0
    const distance = new Array(N + 1).fill(Infinity)
    const previous = new Array(N + 1).fill(-1)
    const pq = new PQ(compare)
    pq.enqueue([start, 0])
    distance[start] = 0

    while (pq.size > 0) {
        const [source, totalDist] = pq.dequeue()
        if (totalDist > distance[source]) continue

        graph[source].forEach((localCost, target) => {
            if (localCost === null) return
            const newCost = localCost + totalDist
            if (newCost > distance[target]) {
                return
            }
            count++
            distance[target] = newCost
            pq.enqueue([target, newCost])
            previous[target] = source
        })
    }
    console.log('cnt:', count)
    return { distance: distance, previous: previous }
}

const firstRes = dijkstra(1)

// const originDist = firstRes.distance[N]

// const path = []
// let dest = N
// while (dest !== 1) {
//     const src = dest
//     dest = firstRes.previous[dest]
//     path.push([src, dest])
// }

// let maxLatency = 0
// for (let v of path) {
//     const [src, dest] = v
//     const cost = graph[src][dest]
//     graph[src][dest] = null
//     graph[dest][src] = null

//     const { distance, previous } = dijkstra(1)
//     if (distance[N] === Infinity) {
//         console.log(-1)
//         return
//     }
//     maxLatency = Math.max(maxLatency, distance[N] - originDist)

//     graph[src][dest] = cost
//     graph[dest][src] = cost
// }

// console.log(maxLatency)
